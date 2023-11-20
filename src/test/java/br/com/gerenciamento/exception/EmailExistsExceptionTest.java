package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailExistsExceptionTest {

    @Test
    public void testConstructor() {
        String message = "Email já existe";
        EmailExistsException exception = new EmailExistsException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSerialVersionUID() {
        long expectedSerialVersionUID = 1L;

        assertEquals(expectedSerialVersionUID, EmailExistsException.getSerialVersionUID());
    }

    @Test
    public void testNullMessage() {
        EmailExistsException exception = new EmailExistsException(null);
    
        assertNull(exception.getMessage());
    }
}
