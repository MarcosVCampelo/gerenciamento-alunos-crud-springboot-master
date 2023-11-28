package br.com.gerenciamento.repository; 

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlunoRepositoryTest {

    private AlunoRepository alunoRepository;

    @BeforeEach
    void setUp() {
        alunoRepository = mock(AlunoRepository.class);
    }

     private Aluno createAluno(String nome, String matricula, Curso curso, Status status, Turno turno) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setCurso(curso);
        aluno.setStatus(status);
        aluno.setTurno(turno);
        return aluno;
    }

    @Test
    void testFindByStatusAtivo() {
        List<Aluno> alunosAtivos = Arrays.asList(
                createAluno("João Silva", "123ABC", Curso.ADMINISTRACAO, Status.ATIVO, Turno.MATUTINO),
                createAluno("Maria Oliveira", "456DEF", Curso.ADMINISTRACAO, Status.ATIVO, Turno.NOTURNO)
        );
        when(alunoRepository.findByStatusAtivo()).thenReturn(alunosAtivos);
        List<Aluno> result = alunoRepository.findByStatusAtivo();
        assertEquals(alunosAtivos, result);
    }

    @Test
    void testFindByStatusInativo() {
        List<Aluno> alunosInativos = Arrays.asList(
                createAluno("Pedro Santos", "789GHI", Curso.DIREITO, Status.INATIVO, Turno.NOTURNO),
                createAluno("Ana Lima", "101JKL", Curso.CONTABILIDADE, Status.INATIVO, Turno.MATUTINO)
        );
        when(alunoRepository.findByStatusInativo()).thenReturn(alunosInativos);
        List<Aluno> result = alunoRepository.findByStatusInativo();
        assertEquals(alunosInativos, result);
    }
    @Test
    void testFindByNomeContainingIgnoreCase() {
        List<Aluno> alunosComJose = Arrays.asList(
                createAluno("José Silva", "111AAA", Curso.BIOMEDICINA, Status.ATIVO, Turno.MATUTINO),
                createAluno("Maria José", "222BBB", Curso.ADMINISTRACAO, Status.ATIVO, Turno.NOTURNO)
        );
        when(alunoRepository.findByNomeContainingIgnoreCase("José")).thenReturn(alunosComJose);
        List<Aluno> result = alunoRepository.findByNomeContainingIgnoreCase("José");
        assertEquals(alunosComJose, result);
    }  
}