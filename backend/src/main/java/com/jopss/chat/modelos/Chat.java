package com.jopss.chat.modelos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jopss.chat.modelos.enums.ChatSituacaoEnum;
import com.jopss.chat.util.JsonDateDeserializer;
import com.jopss.chat.util.JsonDateSerializer;
import com.jopss.chat.util.Modelos;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Chat extends Modelos {

        @Id
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "chatGenerator")
        @TableGenerator(name = "chatGenerator", allocationSize = 1)
        private Long id;

        @NotEmpty
        @NotNull
        private String descricao;
        
        @NotNull
        @Enumerated(EnumType.STRING)
        private ChatSituacaoEnum situacao;

        @Future
        @NotNull
        @Temporal(TemporalType.DATE)
        @JsonSerialize(using = JsonDateSerializer.class)
        @JsonDeserialize(using = JsonDateDeserializer.class)
        private Date dateFinalizacao;

        @OneToMany(mappedBy = "chat", orphanRemoval = true, cascade = CascadeType.ALL)
        private List<Mensagem> mensagens;

        public Chat() {
        }

        public Chat(Long id) {
                this.id = id;
        }

        public void limparListaPalpites() {
                if (this.getMensagens() != null) {
                        this.getMensagens().clear(); //forca o cascade
                }
        }
        
        public void preencherPalpites() {
                for (Mensagem m : this.getMensagens()) {
                        m.setChat(this);
                }
        }
        
        @Override
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public Date getDateFinalizacao() {
                return dateFinalizacao;
        }

        public void setDateFinalizacao(Date dateFinalizacao) {
                this.dateFinalizacao = dateFinalizacao;
        }

        public List<Mensagem> getMensagens() {
                return mensagens;
        }

        public void setMensagens(List<Mensagem> mensagens) {
                this.mensagens = mensagens;
        }

        public ChatSituacaoEnum getSituacao() {
                return situacao;
        }

        public void setSituacao(ChatSituacaoEnum situacao) {
                this.situacao = situacao;
        }

}
