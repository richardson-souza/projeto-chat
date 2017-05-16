package com.jopss.chat.web.form;

public class WebsocketForm {
        
        private String mensagem;

        public String getMensagem() {
                return mensagem;
        }

        public void setMensagem(String mensagem) {
                this.mensagem = mensagem;
        }

        @Override
        public String toString() {
                return "Mensagem: "+mensagem;
        }
        
}
