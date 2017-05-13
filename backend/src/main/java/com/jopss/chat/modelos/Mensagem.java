package com.jopss.chat.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.chat.util.Modelos;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Mensagem extends Modelos {
        
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "mensagemGenerator")
	@TableGenerator(name = "mensagemGenerator", allocationSize = 1)
	private Long id;
        
        @Size(max = 2000)
        @NotNull
        @NotEmpty
        private String texto;
        
        @NotNull
        @ManyToOne
        private Usuario usuario;
        
        @NotNull
        @ManyToOne
        @JsonIgnore
        private Chat chat;

        public Mensagem() {
        }

        @Override
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Usuario getUsuario() {
                return usuario;
        }

        public void setUsuario(Usuario usuario) {
                this.usuario = usuario;
        }

        public String getTexto() {
                return texto;
        }

        public void setTexto(String texto) {
                this.texto = texto;
        }

        public Chat getChat() {
                return chat;
        }

        public void setChat(Chat chat) {
                this.chat = chat;
        }

}
