package com.jopss.chat.servicos.security;

import com.jopss.chat.excecoes.TokenExpiradoException;
import com.jopss.chat.excecoes.TokenInvalidoException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.util.LinkedMultiValueMap;

public class SegurancaInterceptorWebsocket extends ChannelInterceptorAdapter {
        private Logger log = Logger.getLogger(SegurancaInterceptorWebsocket.class);
        
        @Autowired
        private SegurancaServico segurancaServico;
        
        @Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
                MessageHeaders headers = message.getHeaders();
		LinkedMultiValueMap lista = (LinkedMultiValueMap) headers.get("nativeHeaders");
                String token = (String) lista.getFirst("token");
                if(token!=null){
                        log.info("Carregando token"+token);
                        token = token.trim();

                        try {
                                segurancaServico.verificaValidadeTokenAdicionandoNoContexto(token);
                        } catch (TokenExpiradoException | TokenInvalidoException ex) {
                                log.error("Erro de Token em SegurancaInterceptorWebsocket.");
                        }
                }
                return super.preSend(message, channel);
        }

}