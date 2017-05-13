package com.jopss.chat.web;

import com.jopss.chat.excecoes.ChatException;
import com.jopss.chat.modelos.enums.RoleEnum;
import com.jopss.chat.servicos.repositorio.ChatRepository;
import com.jopss.chat.servicos.security.annotation.Privado;
import com.jopss.chat.servicos.security.annotation.Publico;
import com.jopss.chat.web.form.Resposta;
import com.jopss.chat.web.util.ChatAppController;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class ChatController extends ChatAppController {
        
        @Autowired
        private ChatRepository chatRepository;

        @Publico
        @ResponseBody
        @RequestMapping(value = "/teste", method = RequestMethod.GET)
        public Resposta teste(HttpServletResponse resp) {
                Resposta resposta = new Resposta();
                resposta.setMensagemSucesso("mensagem.teste", resp);
                return resposta;
        }

        @Privado(role = RoleEnum.ROLE_GERAL)
        @ResponseBody
        @RequestMapping(value = "/todos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public Resposta buscarPorPagina(HttpServletResponse resp) throws ChatException {
                Resposta resposta = new Resposta();
                resposta.setLista(IteratorUtils.toList(chatRepository.findAll().iterator()), resp);
                return resposta;
        }

}
