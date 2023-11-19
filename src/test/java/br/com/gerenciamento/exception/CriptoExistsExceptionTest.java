package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CriptoExistsExceptionTest {

    @Test //verifica se o construtor está configurando corretamente a mensagem
    public void testConstructor() {
        String message = "ERRO";
        CriptoExistsException exception = new CriptoExistsException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test //verifica se serialVersionUID está configurado corretamente
    public void testSerialVersionUID() {
        long expectedSerialVersionUID = 1L;

        assertEquals(expectedSerialVersionUID, CriptoExistsException.getSerialVersionUID());
    }

    @Test //verifica campo nulo
    public void testNullMessage() {
        CriptoExistsException exception = new CriptoExistsException(null);
    
        assertNull(exception.getMessage());
    }
}

