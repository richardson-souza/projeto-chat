package com.jopss.chat.web.form;

import com.jopss.chat.modelos.Usuario;
import java.util.Objects;
import javax.websocket.Session;

public class WebsocketForm {
        
        private Usuario usuario;
        private Session sessao;

        public WebsocketForm(Usuario usuario, Session sessao) {
                this.usuario = usuario;
                this.sessao = sessao;
        }

        public WebsocketForm(Session sessao) {
                this.sessao = sessao;
        }

        public Usuario getUsuario() {
                return usuario;
        }

        public Session getSessao() {
                return sessao;
        }

        @Override
        public int hashCode() {
                int hash = 7;
                hash = 47 * hash + Objects.hashCode(this.sessao.getId());
                return hash;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj) {
                        return true;
                }
                if (obj == null) {
                        return false;
                }
                if (getClass() != obj.getClass()) {
                        return false;
                }
                final WebsocketForm other = (WebsocketForm) obj;
                if (!Objects.equals(this.sessao.getId(), other.sessao.getId())) {
                        return false;
                }
                return true;
        }
        
}
