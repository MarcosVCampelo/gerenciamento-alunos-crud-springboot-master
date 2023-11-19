package br.com.gerenciamento.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceExcTest {

    @Test //verifica se o construtor está configurando corretamente a mensagem
    public void testConstructor() {
        String message = "Mensagem de erro";
        ServiceExc exception = new ServiceExc(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test //verifica se serialVersionUID está configurado corretamente
    public void testSerialVersionUID() {
        long expectedSerialVersionUID = 1L;

        assertEquals(expectedSerialVersionUID, ServiceExc.getSerialVersionUID());
    }
}
