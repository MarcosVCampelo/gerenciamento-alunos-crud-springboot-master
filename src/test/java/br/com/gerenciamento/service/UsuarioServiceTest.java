package br.com.gerenciamento.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.gerenciamento.exception.ServiceExc;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.repository.UsuarioRepository;


public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testslavarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(null);

        assertDoesNotThrow(() -> usuarioService.salvarUsuario(usuario));

        verify(usuarioRepository, times(1)).save(eq(usuario));
    }


    @Test
    public void testLoginUser() throws ServiceExc {
        String user = "testuser";
        String senha = "testpassword";
        Usuario expectedUser = new Usuario();
        when(usuarioRepository.buscarLogin(user, senha)).thenReturn(expectedUser);

        Usuario result = usuarioService.loginUser(user, senha);

        assertEquals(expectedUser, result);

        verify(usuarioRepository, times(1)).buscarLogin(user, senha);
    }
}