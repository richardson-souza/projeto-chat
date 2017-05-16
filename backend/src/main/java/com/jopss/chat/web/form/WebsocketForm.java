package com.jopss.chat.web.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.chat.modelos.Usuario;
import com.jopss.chat.modelos.enums.SituacaoUsuarioFormEnum;

public class WebsocketForm {
        
        private String mensagem;
        
        private String nomeUsuario;
        private String idUsuario;
        private String situacaoUsuario;

        public WebsocketForm() {
        }

        public WebsocketForm(String mensagem, Usuario usuario) {
                this(usuario);
                this.mensagem = mensagem;
        }
        
        public WebsocketForm(Usuario usuario) {
                this.idUsuario = ""+usuario.getId();
                this.nomeUsuario = usuario.getNome();
                this.situacaoUsuario = SituacaoUsuarioFormEnum.ATIVO.name();
        }
        
        public WebsocketForm(String mensagem) {
                this.mensagem = mensagem;
                this.situacaoUsuario = SituacaoUsuarioFormEnum.INATIVO.name();
        }

        public String getMensagem() {
                return mensagem;
        }

        public void setMensagem(String mensagem) {
                this.mensagem = mensagem;
        }

        @JsonIgnore
        public SituacaoUsuarioFormEnum getSituacaoUsuarioEnum() {
                return SituacaoUsuarioFormEnum.valueOf(situacaoUsuario);
        }
        
        public String getSituacaoUsuario() {
                return situacaoUsuario;
        }

        public void setSituacaoUsuarioEnum(SituacaoUsuarioFormEnum situacaoUsuario) {
                this.situacaoUsuario = situacaoUsuario.name();
        }

        public String getNomeUsuario() {
                return nomeUsuario;
        }

        public void setNomeUsuario(String nomeUsuario) {
                this.nomeUsuario = nomeUsuario;
        }

        public String getIdUsuario() {
                return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
                this.idUsuario = idUsuario;
        }

        @Override
        public String toString() {
                return "Mensagem: "+mensagem;
        }
        
}
