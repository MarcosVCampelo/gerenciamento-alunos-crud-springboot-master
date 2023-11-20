package br.com.gerenciamento.model;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test //valida campo nome
    void testSetAndGetNome() {
        Aluno aluno = new Aluno();
        aluno.setNome("João Silva");
        assertEquals("João Silva", aluno.getNome());
    }

    @Test //valida campo curso
    void testSetAndGetCurso() {
        Aluno aluno = new Aluno();
        aluno.setCurso(Curso.ADMINISTRACAO);
        assertEquals(Curso.ADMINISTRACAO, aluno.getCurso());
    }

    @Test //valida campo ativo/inativo
    void testSetAndGetStatus() {
        Aluno aluno = new Aluno();
        aluno.setStatus(Status.ATIVO);
        assertEquals(Status.ATIVO, aluno.getStatus());
    }

    @Test //valida campo período
    void testSetAndGetTurno() {
        Aluno aluno = new Aluno();
        aluno.setTurno(Turno.NOTURNO);
        assertEquals(Turno.NOTURNO, aluno.getTurno());
    }

    @Test //valida id do aluno
    void testSetAndGetId() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        assertEquals(1L, aluno.getId());
    }

    @Test //valida minimo de caracteres
    void testNomeMinLengthValidation() {
        Aluno aluno = new Aluno();
        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            aluno.setNome("Jo");
        });
    }
}
