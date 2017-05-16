package com.jopss.chat.web.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.chat.modelos.Usuario;
import com.jopss.chat.modelos.enums.SituacaoUsuarioFormEnum;
import java.util.Date;

public class WebsocketForm {
        
        private Date dataMensagem = new Date();
        private String mensagem;
        private String tipoMensagem;
        
        private String nomeUsuario;
        private String idUsuario;
        private String situacaoUsuario;

        public WebsocketForm() {
        }

        public WebsocketForm(String tipoMensagem, String mensagem, Usuario usuario) {
                this(tipoMensagem, usuario);
                this.mensagem = mensagem;
        }
        
        public WebsocketForm(String tipoMensagem, Usuario usuario) {
                this.tipoMensagem = tipoMensagem;
                this.idUsuario = ""+usuario.getId();
                this.nomeUsuario = usuario.getNome();
                this.situacaoUsuario = SituacaoUsuarioFormEnum.ATIVO.name();
        }
        
        public WebsocketForm(String tipoMensagem, String mensagem) {
                this.tipoMensagem = tipoMensagem;
                this.mensagem = mensagem;
                this.situacaoUsuario = SituacaoUsuarioFormEnum.INATIVO.name();
        }

        public Date getDataMensagem() {
                return dataMensagem;
        }

        public void setDataMensagem(Date dataMensagem) {
                this.dataMensagem = dataMensagem;
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

        public String getTipoMensagem() {
                return tipoMensagem;
        }

        public void setTipoMensagem(String tipoMensagem) {
                this.tipoMensagem = tipoMensagem;
        }

        @Override
        public String toString() {
                return "Mensagem: "+mensagem;
        }
        
}
