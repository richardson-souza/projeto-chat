package com.jopss.chat.web;

import com.jopss.chat.servicos.security.SegurancaServico;
import com.jopss.chat.web.form.WebsocketForm;
import com.jopss.chat.web.util.ChatAppController;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController extends ChatAppController {

        private static Set<WebsocketForm> clientes = Collections.synchronizedSet(new HashSet<WebsocketForm>());
        private Logger log = Logger.getLogger(WebsocketController.class);

        @Autowired
        private SegurancaServico segurancaServico;

        @MessageMapping("/enviar")
        @SendTo("/canal/chat")
        public String sendMessage(String message) {
                log.info("Message: " + message);
                return "ok";
        }

}
