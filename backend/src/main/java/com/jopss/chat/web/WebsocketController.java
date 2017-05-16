package com.jopss.chat.web;

import com.jopss.chat.excecoes.TokenInvalidoException;
import com.jopss.chat.modelos.SegurancaAPI;
import com.jopss.chat.web.form.WebsocketForm;
import com.jopss.chat.web.util.ChatAppController;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class WebsocketController extends ChatAppController {

        @MessageMapping("/mensagem/enviar")
        @SendTo("/subscribe/canal/chat")
        public WebsocketForm receberMensagem(WebsocketForm form, MessageHeaders messageHeaders) {
                try {
                        SegurancaAPI logado = this.segurancaServico.getUsuarioLogado(messageHeaders);
                        return new WebsocketForm(form.getMensagem(), logado.getUsuario());
                } catch (TokenInvalidoException ex) {
                        return new WebsocketForm(form.getMensagem());
                }
        }

}
