package br.com.gerenciamento.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.NoSuchAlgorithmException;

import br.com.gerenciamento.exception.ServiceExc;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.repository.UsuarioRepository;
import br.com.gerenciamento.service.PrintService;
import br.com.gerenciamento.service.UsuarioService;

import org.hibernate.jdbc.Expectations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class UsuarioControllerTest {

    private UsuarioController usuarioController;

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private PrintService printService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = mock(UsuarioService.class);
        printService = mock(PrintService.class);

        usuarioController = new UsuarioController();
    }
    

    @Test
    void testLoginWithErrors() throws NoSuchAlgorithmException, ServiceExc {
        HttpSession session = mock(HttpSession.class);
        Usuario usuario = new Usuario();

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = usuarioController.login(usuario, bindingResult, session);

        assertEquals("redirect:/login", modelAndView.getViewName());
    }


    @Test
    public void testLogin() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.view().name("login/login"));
    }

    @Test
    public void testLogout() throws Exception {
     MockMvc mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(post("/logout"))
               .andExpect(status().isOk())
               .andExpect(view().name("login/login"));
    }

    @Test
    public void testCadastro() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/cadastro"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("login/cadastro"));

    }

    @Test
    public void index() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(view().name("home/index"));
    }
}

