package br.com.gerenciamento.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnoTest {

    @Test
    public void testTurnoEnumValues() {
        Turno[] turnos = Turno.values();

        assertNotNull(turnos);
        assertEquals(2, turnos.length);
        assertEquals(Turno.MATUTINO, Turno.valueOf("MATUTINO"));
        assertEquals(Turno.NOTURNO, Turno.valueOf("NOTURNO"));
    }

    @Test
    public void testTurnoEnumStringRepresentation() {
        assertEquals("MATUTINO", Turno.MATUTINO.toString());
        assertEquals("NOTURNO", Turno.NOTURNO.toString());
    }
}