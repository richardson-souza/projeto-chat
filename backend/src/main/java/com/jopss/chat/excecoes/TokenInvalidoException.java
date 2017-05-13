package com.jopss.chat.excecoes;

public class TokenInvalidoException extends ChatException {

        public TokenInvalidoException(String message) {
                super(message);
        }

        public TokenInvalidoException(Throwable cause) {
                super(cause);
        }
        
}