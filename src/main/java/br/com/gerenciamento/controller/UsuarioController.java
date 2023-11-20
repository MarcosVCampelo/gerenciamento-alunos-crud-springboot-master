package br.com.gerenciamento.controller;

import br.com.gerenciamento.repository.UsuarioRepository;
import br.com.gerenciamento.exception.ServiceExc;
import br.com.gerenciamento.service.PrintService;
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.service.UsuarioService;
import br.com.gerenciamento.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {

  @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService serviceUsuario;


    private PrintService printar = new PrintService();


    @GetMapping("/login")
    public ModelAndView login() {
        return printar.modelAndView("login/login", new Usuario(),"usuario");
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return printar.modelAndView("home/index", new Aluno(), "aluno");
    }


    @GetMapping("/cadastro")
    public ModelAndView cadastrar() {
        return printar.modelAndView("login/cadastro", new Usuario(), "usuario");
    }

  @PostMapping("/salvarUsuario")

    public ModelAndView cadastrar(Usuario usuario) throws Exception {
        serviceUsuario.salvarUsuario(usuario);
       return printar.modelAndView("redirect:/login", usuario, "usuario");
    }


    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br,
                              HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usuario", new Usuario());
        if(br.hasErrors()) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        Usuario userLogin = serviceUsuario.loginUser(usuario.getUser(), Util.md5(usuario.getSenha()));
        if(userLogin == null) {
            modelAndView.addObject("msg","Usuario não encontrado. Tente novamente");
        } else {
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }

        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }
}
