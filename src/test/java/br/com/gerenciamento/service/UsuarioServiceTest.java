package br.com.gerenciamento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gerenciamento.exception.ServiceExc;
import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
   
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testSalvarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setUser("testuser");
        usuario.setSenha("password");
        when(usuarioRepository.findByEmail(eq("test@example.com"))).thenReturn(null);
        usuarioService.salvarUsuario(usuario);
        verify(usuarioRepository, times(1)).findByEmail(eq("test@example.com"));
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
    @Test
    public void testLoginUser() throws ServiceExc {
        String usuario = "username";
        String senha = "password";
        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setUser(usuario);
        usuarioSimulado.setSenha(senha);
        when(usuarioRepository.buscarLogin(usuario, senha)).thenReturn(usuarioSimulado);
        Usuario resultado = usuarioService.loginUser(usuario, senha);
        assertEquals(usuarioSimulado, resultado);
        verify(usuarioRepository, times(1)).buscarLogin(usuario, senha);
    }
}