package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailExistsExceptionTest {

    @Test //verifica se o construtor está configurando corretamente a mensagem
    public void testConstructor() {
        String message = "Email já existe";
        EmailExistsException exception = new EmailExistsException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test //verifica se o serialVersionUID está configurado corretamente
    public void testSerialVersionUID() {
        long expectedSerialVersionUID = 1L;

        assertEquals(expectedSerialVersionUID, EmailExistsException.getSerialVersionUID());
    }

    @Test //verifica campo nulo
    public void testNullMessage() {
        EmailExistsException exception = new EmailExistsException(null);
    
        assertNull(exception.getMessage());
    }
}
