package com.jopss.chat.servicos.security;

import com.jopss.chat.excecoes.TokenExpiradoException;
import com.jopss.chat.excecoes.TokenInvalidoException;
import com.jopss.chat.modelos.SegurancaAPI;
import com.jopss.chat.web.form.WebsocketForm;
import java.util.LinkedList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.util.LinkedMultiValueMap;

public class SegurancaInterceptorWebsocket extends ChannelInterceptorAdapter {

        private Logger log = Logger.getLogger(SegurancaInterceptorWebsocket.class);

        @Autowired
        private SegurancaServico segurancaServico;

        @Autowired
        private SimpMessagingTemplate messagingTemplate;

        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
                log.info("WEBSOCKET PRE-SEND.");
                MessageHeaders headers = message.getHeaders();
                SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
                LinkedMultiValueMap lista = (LinkedMultiValueMap) headers.get("nativeHeaders");
                String token = (String) lista.getFirst("token");

                if (type == SimpMessageType.CONNECT) {
                        log.info("CONNECT: Carregando token" + token);
                        if (token != null) {
                                token = token.trim();

                                try {
                                        segurancaServico.verificaValidadeTokenAdicionandoNoContexto(token);
                                } catch (TokenExpiradoException | TokenInvalidoException ex) {
                                        log.error("Erro de Token em SegurancaInterceptorWebsocket.");
                                }
                        }
                } else if (type == SimpMessageType.DISCONNECT) {
                        log.info("DISCONNECT: token" + token);
                        SegurancaAPIThreadLocal.remove(token);
                        this.messagingTemplate.convertAndSend("/subscribe/canal/chat", new WebsocketForm("DISCONNECT"));
                }

                return super.preSend(message, channel);
        }

        @Override
        public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
                log.info("WEBSOCKET POST-SEND.");
                MessageHeaders headers = message.getHeaders();
                SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
                Map mapa = (Map) headers.get("nativeHeaders");
                LinkedList lista = (LinkedList) mapa.get("token");
                String token = (String) lista.iterator().next();
                
                if (type == SimpMessageType.SUBSCRIBE) {
                        log.info("SUBSCRIBE");
                        if (token != null) {
                                token = token.trim();

                                try {
                                        SegurancaAPI seg = segurancaServico.verificaValidadeTokenAdicionandoNoContexto(token);
                                        log.info("INFORMANDO USUARIO ATIVO");
                                        this.messagingTemplate.convertAndSend("/subscribe/canal/chat", new WebsocketForm("CONNECT", seg.getUsuario()));
                                } catch (TokenExpiradoException | TokenInvalidoException ex) {
                                        log.error("Erro de Token em SegurancaInterceptorWebsocket.");
                                }
                        }
                }
        }

}
