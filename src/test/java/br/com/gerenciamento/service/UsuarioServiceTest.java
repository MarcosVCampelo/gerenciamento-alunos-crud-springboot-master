package br.com.gerenciamento.service;

import static org.mockito.Mockito.*;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setUser("testuser");
        usuario.setSenha("password");

        // Mocking
        when(usuarioRepository.findByEmail(eq("test@example.com"))).thenReturn(null);

        // Act
        usuarioService.salvarUsuario(usuario);

        // Assert
        verify(usuarioRepository, times(1)).findByEmail(eq("test@example.com"));
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}