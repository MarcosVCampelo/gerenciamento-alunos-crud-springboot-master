package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CriptoExistsExceptionTest {

    @Test
    public void testConstructor() {
        String message = "ERRO";
        CriptoExistsException exception = new CriptoExistsException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void testSerialVersionUID() {
        long expectedSerialVersionUID = 1L;

        assertEquals(expectedSerialVersionUID, CriptoExistsException.getSerialVersionUID());
    }

    @Test
    public void testNullMessage() {
        CriptoExistsException exception = new CriptoExistsException(null);
    
        assertNull(exception.getMessage());
    }
}

