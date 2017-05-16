package com.jopss.chat.web;

import com.jopss.chat.excecoes.TokenInvalidoException;
import com.jopss.chat.modelos.Mensagem;
import com.jopss.chat.modelos.SegurancaAPI;
import com.jopss.chat.servicos.security.SegurancaServico;
import com.jopss.chat.web.form.WebsocketForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class WebsocketController {
        private Logger log = Logger.getLogger(WebsocketController.class);

        @Autowired
        protected SegurancaServico segurancaServico;
        
        @MessageMapping("/mensagem/enviar")
        @SendTo("/subscribe/canal/chat")
        public Mensagem receberMensagem(WebsocketForm form) throws TokenInvalidoException {
//                SegurancaAPI logado = this.segurancaServico.getUsuarioLogado();
                log.info("Message: " + form+", Usuario: "+null);
                return new Mensagem(form.getMensagem(), null);
        }

}
