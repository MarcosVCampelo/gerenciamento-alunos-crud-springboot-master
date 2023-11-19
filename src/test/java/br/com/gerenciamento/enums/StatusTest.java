package br.com.gerenciamento.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatusTest {

    @Test
    public void testStatusEnumValues() {
        Status[] statuses = Status.values();

        assertNotNull(statuses);
        assertEquals(2, statuses.length);
        assertEquals(Status.ATIVO, Status.valueOf("ATIVO"));
        assertEquals(Status.INATIVO, Status.valueOf("INATIVO"));
    }

    @Test
    public void testStatusEnumStringRepresentation() {
        assertEquals("ATIVO", Status.ATIVO.toString());
        assertEquals("INATIVO", Status.INATIVO.toString());
    }
}
