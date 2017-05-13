package com.jopss.chat.excecoes;

public class UsuarioNaoLogadoException extends RuntimeException{
        
        public UsuarioNaoLogadoException(){
                super();
        }
        
        public UsuarioNaoLogadoException(String message){
                super(message);
        }
}
