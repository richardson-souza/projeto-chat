package com.jopss.chat.web;

import com.jopss.chat.excecoes.TokenInvalidoException;
import com.jopss.chat.modelos.Perfil;
import com.jopss.chat.modelos.Usuario;
import com.jopss.chat.modelos.enums.RoleEnum;
import com.jopss.chat.servicos.repositorio.PerfilRepository;
import com.jopss.chat.servicos.repositorio.UsuarioRepository;
import com.jopss.chat.servicos.security.annotation.Privado;
import com.jopss.chat.web.form.Resposta;
import com.jopss.chat.web.util.ChatAppController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController extends ChatAppController {

        @Autowired
        private UsuarioRepository usuarioRepository;
        
        @Autowired
        private PerfilRepository perfilRepository;

        @Privado(role = RoleEnum.ROLE_GERAL)
        @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public Resposta salvar(@RequestBody Usuario usuario, HttpServletResponse resp, HttpSession session) {
                usuario.encriptarSenha();
                usuario = this.usuarioRepository.save(usuario);

                Resposta resposta = new Resposta();
                resposta.setDado(usuario, resp, "usuario.sucesso");
                return resposta;
        }

        @Privado(role = RoleEnum.ROLE_GERAL)
        @RequestMapping(value = "/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public Resposta buscarTodos(HttpServletResponse resp, HttpSession session) {
                List<Usuario> lista = IteratorUtils.toList(this.usuarioRepository.findAll().iterator());

                Resposta resposta = new Resposta();
                resposta.setLista(lista, resp);
                return resposta;
        }

        @Privado(role = RoleEnum.ROLE_GERAL)
        @RequestMapping(value = "/perfis/todos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public Resposta buscarTodosPerfis(HttpServletResponse resp, HttpSession session) {
                List<Perfil> lista = IteratorUtils.toList(this.perfilRepository.findAll().iterator());

                Resposta resposta = new Resposta();
                resposta.setLista(lista, resp);
                return resposta;
        }
        
        @Privado(role = RoleEnum.ROLE_GERAL)
        @RequestMapping(value = "/logado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public Resposta buscarLogado(HttpServletResponse resp, HttpServletRequest req, HttpSession session) throws TokenInvalidoException {
                Usuario usu = super.segurancaServico.getUsuarioLogado(req).getUsuario();
                usu.setSenha(null);

                Resposta resposta = new Resposta();
                resposta.setDado(usu, resp);
                return resposta;
        }

}
