package com.jopss.chat.excecoes;

public class ChatException extends Exception {

        public ChatException() {
                super();
        }
        
        public ChatException(String message) {
                super(message);
        }

        public ChatException(Throwable cause) {
                super(cause);
        }
        
}
