package br.com.gerenciamento.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

public class CriptoExistsExceptionTest {

    @Test
    public void testCriptoExistsException() {
        // Mensagem de teste
        String mensagem = "Mensagem de teste";

        // Criação de uma instância da exceção
        CriptoExistsException criptoExistsException = new CriptoExistsException(mensagem);

        // Verificação se a mensagem passada ao construtor está correta
        assertEquals(mensagem, criptoExistsException.getMessage());
    }

    @Test
    public void testSerialVersionUID() throws NoSuchFieldException, IllegalAccessException {
        // Obter o campo serialVersionUID usando reflexão
        Field field = CriptoExistsException.class.getDeclaredField("serialVersionUID");
        field.setAccessible(true);

        // Verifica se o serialVersionUID é o esperado
        assertEquals(1L, field.get(null));
    }
}
