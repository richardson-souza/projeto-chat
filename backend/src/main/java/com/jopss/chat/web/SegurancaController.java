package com.jopss.chat.web;

import com.jopss.chat.excecoes.TokenInvalidoException;
import com.jopss.chat.modelos.enums.RoleEnum;
import com.jopss.chat.servicos.security.annotation.Privado;
import com.jopss.chat.servicos.security.annotation.Publico;
import com.jopss.chat.web.form.LoginForm;
import com.jopss.chat.web.form.Resposta;
import com.jopss.chat.web.form.RetornoLoginForm;
import com.jopss.chat.web.util.ChatAppController;
import com.jopss.chat.web.util.PrettyWrappedRequestLoginOauth;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/usuario")
public class SegurancaController extends ChatAppController {

        @Publico
        @ResponseBody
        @RequestMapping(value = "/logar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Resposta logar(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse resp) throws IllegalArgumentException, IllegalAccessException {
                //Ao injetar a request, os parametros da request vem vazio. 
		//Usa entao a classe abaixo para "readicionar" os params dentro da request, pegando no metodo com JSON.
		PrettyWrappedRequestLoginOauth req = new PrettyWrappedRequestLoginOauth(request, loginForm);
                RetornoLoginForm retorno = segurancaServico.logarOAuth(req, resp);

                Resposta resposta = new Resposta();
                if(retorno.getStatus() == 200){
                        resposta.setDado(retorno, resp, retorno.getStatus(), retorno.getMensagemDescricao());
                }else{
                        resposta.setDado(retorno);
                }
		return resposta;
        }

        /**
         * Acao que verifica periodicamente se ha usuario logado.
         */
        @Privado(role = RoleEnum.ROLE_GERAL)
        @ResponseBody
        @RequestMapping(value = "/verificar/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public Resposta verificar(HttpServletResponse resp) throws TokenInvalidoException {
                RetornoLoginForm retorno = new RetornoLoginForm(super.segurancaServico.getUsuarioLogado().getUsuario());
                
                Resposta resposta = new Resposta();
		resposta.setDado(retorno, resp, retorno.getStatus(), retorno.getMensagemDescricao());
		return resposta;
        }

}
