package br.com.gerenciamento.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CursoTest {

    @Test
    public void testCursoEnumValues() {
        Curso[] cursos = Curso.values();

        assertNotNull(cursos);
        assertEquals(6, cursos.length);
        assertEquals(Curso.ADMINISTRACAO, Curso.valueOf("ADMINISTRACAO"));
        assertEquals(Curso.INFORMATICA, Curso.valueOf("INFORMATICA"));
        assertEquals(Curso.CONTABILIDADE, Curso.valueOf("CONTABILIDADE"));
        assertEquals(Curso.ENFERMAGEM, Curso.valueOf("ENFERMAGEM"));
        assertEquals(Curso.BIOMEDICINA, Curso.valueOf("BIOMEDICINA"));
        assertEquals(Curso.DIREITO, Curso.valueOf("DIREITO"));
    }

    @Test
    public void testCursoEnumStringRepresentation() {
        assertEquals("ADMINISTRACAO", Curso.ADMINISTRACAO.toString());
        assertEquals("INFORMATICA", Curso.INFORMATICA.toString());
        assertEquals("CONTABILIDADE", Curso.CONTABILIDADE.toString());
        assertEquals("ENFERMAGEM", Curso.ENFERMAGEM.toString());
        assertEquals("BIOMEDICINA", Curso.BIOMEDICINA.toString());
        assertEquals("DIREITO", Curso.DIREITO.toString());
    }
}