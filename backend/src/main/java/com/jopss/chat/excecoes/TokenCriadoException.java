package com.jopss.chat.excecoes;

import com.jopss.chat.modelos.SegurancaAPI;

public class TokenCriadoException extends ChatException {

        private SegurancaAPI segurancaAPI;
        
        public TokenCriadoException(SegurancaAPI segurancaAPI) {
                super("Token ja criado para este usuario.");
                this.segurancaAPI = segurancaAPI;
        }

        public SegurancaAPI getSegurancaAPI() {
                return segurancaAPI;
        }
}