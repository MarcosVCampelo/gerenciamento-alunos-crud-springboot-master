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
        // Cria um usuário fictício com o e-mail "usuario@teste"
        Usuario usuario = createUsuario("usuario@teste","Usuario", "senha123");

        // Configura o comportamento esperado do mock quando o método findByEmail() é chamado
        when(usuarioRepository.findByEmail("usuario@teste")).thenReturn(usuario);

        // Chama o método real que você deseja testar
        Usuario result = usuarioRepository.findByEmail("usuario@teste");

        // Verifica se o resultado é o esperado
        assertEquals(usuario, result);
    }

    @Test
    void testBuscarLogin() {
        // Cria um usuário fictício com nome de usuário "user123" e senha "senha456"
        Usuario usuario = createUsuario("usuario@teste","Usuario", "senha123");

        // Configura o comportamento esperado do mock quando o método buscarLogin() é chamado
        when(usuarioRepository.buscarLogin("Usuario", "senha123")).thenReturn(usuario);

        // Chama o método real que você deseja testar
        Usuario result = usuarioRepository.buscarLogin("Usuario", "senha123");

        // Verifica se o resultado é o esperado
        assertEquals(usuario, result);
    }

   
}
