package com.jopss.chat.excecoes;

public class UsuarioOuSenhaInvalidaException extends ChatException {

        public UsuarioOuSenhaInvalidaException(String message) {
                super(message);
        }

        public UsuarioOuSenhaInvalidaException(Throwable cause) {
                super(cause);
        }
        
}
