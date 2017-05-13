package com.jopss.chat.excecoes;

public class TokenExpiradoException extends ChatException {

        public TokenExpiradoException(String message) {
                super(message);
        }

        public TokenExpiradoException(Throwable cause) {
                super(cause);
        }
        
}