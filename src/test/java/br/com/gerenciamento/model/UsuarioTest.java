package br.com.gerenciamento.model;

import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test //valida usuario válido
    public void testValidUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@test.com");
        usuario.setUser("username");
        usuario.setSenha("senha123");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertTrue(violations.isEmpty(), "Validação de usuário com sucesso");
    }

    @Test //valida email inválido
    public void testInvalidEmail() {
        Usuario usuario = new Usuario();
        usuario.setEmail("emailinvalido");
        usuario.setUser("username");
        usuario.setSenha("senha123");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertFalse(violations.isEmpty(), "Email inválido");
    }

    @Test //valida tamanho do comprimento de usuário
    public void testInvalidUserLength() {
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@test.com");
        usuario.setUser("us");
        usuario.setSenha("senha123");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertFalse(violations.isEmpty(), "Comprimento do usuário inválido");
    }

    @Test //valida senha inválida
    public void testInvalidSenhaNull() {
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@test.com");
        usuario.setUser("username");
        // Senha é nula

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertTrue(violations.isEmpty(), "Senha inválida");
    }
}
