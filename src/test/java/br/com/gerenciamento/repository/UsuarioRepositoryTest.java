package br.com.gerenciamento.repository;

import br.com.gerenciamento.model.Usuario;
import br.com.gerenciamento.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;

     private Usuario createUsuario(String email, String user, String senha) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setUser(user);
        usuario.setSenha(senha);
        
        return usuario;
    }

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
    }

    @Test
    void testFindByEmail() {
        Usuario usuario = createUsuario("usuario@teste","Usuario", "senha123");
        when(usuarioRepository.findByEmail("usuario@teste")).thenReturn(usuario);
        Usuario result = usuarioRepository.findByEmail("usuario@teste");
        assertEquals(usuario, result);
    }

    @Test
    void testBuscarLogin() {
        Usuario usuario = createUsuario("usuario@teste","Usuario", "senha123");
        when(usuarioRepository.buscarLogin("Usuario", "senha123")).thenReturn(usuario);
        Usuario result = usuarioRepository.buscarLogin("Usuario", "senha123");
        assertEquals(usuario, result);
    }

   
}
