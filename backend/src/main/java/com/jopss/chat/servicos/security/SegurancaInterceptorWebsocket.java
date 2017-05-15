package com.jopss.chat.servicos.security;

import com.jopss.chat.excecoes.TokenExpiradoException;
import com.jopss.chat.excecoes.TokenInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.util.LinkedMultiValueMap;

public class SegurancaInterceptorWebsocket extends ChannelInterceptorAdapter {

        @Autowired
        private SegurancaServico segurancaServico;
        
        @Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
                MessageHeaders headers = message.getHeaders();
		LinkedMultiValueMap lista = (LinkedMultiValueMap) headers.get("nativeHeaders");
                String token = (String) lista.getFirst("token");
                
                try {
                        segurancaServico.verificaValidadeTokenAdicionandoNoContexto(token);
                } catch (TokenExpiradoException | TokenInvalidoException ex) {
                        ex.printStackTrace();
                }
                return super.preSend(message, channel);
        }

}