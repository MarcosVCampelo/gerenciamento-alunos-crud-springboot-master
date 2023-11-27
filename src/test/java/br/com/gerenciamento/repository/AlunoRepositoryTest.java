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
        // Cria uma lista de alunos ativos fictícia
        List<Aluno> alunosAtivos = Arrays.asList(
                createAluno("João Silva", "123ABC", Curso.ADMINISTRACAO, Status.ATIVO, Turno.MATUTINO),
                createAluno("Maria Oliveira", "456DEF", Curso.ADMINISTRACAO, Status.ATIVO, Turno.NOTURNO)
        );

        // Configura o comportamento esperado do mock quando o método findByStatusAtivo() é chamado
        when(alunoRepository.findByStatusAtivo()).thenReturn(alunosAtivos);

        // Chama o método real que você deseja testar
        List<Aluno> result = alunoRepository.findByStatusAtivo();

        // Verifica se o resultado é o esperado
        assertEquals(alunosAtivos, result);
    }

    @Test
    void testFindByStatusInativo() {
        // Cria uma lista de alunos inativos fictícia
        List<Aluno> alunosInativos = Arrays.asList(
                createAluno("Pedro Santos", "789GHI", Curso.DIREITO, Status.INATIVO, Turno.NOTURNO),
                createAluno("Ana Lima", "101JKL", Curso.CONTABILIDADE, Status.INATIVO, Turno.MATUTINO)
        );

        // Configura o comportamento esperado do mock quando o método findByStatusInativo() é chamado
        when(alunoRepository.findByStatusInativo()).thenReturn(alunosInativos);

        // Chama o método real que você deseja testar
        List<Aluno> result = alunoRepository.findByStatusInativo();

        // Verifica se o resultado é o esperado
        assertEquals(alunosInativos, result);
    }
    @Test
    void testFindByNomeContainingIgnoreCase() {
        // Cria uma lista de alunos fictícia contendo "José" no nome
        List<Aluno> alunosComJose = Arrays.asList(
                createAluno("José Silva", "111AAA", Curso.BIOMEDICINA, Status.ATIVO, Turno.MATUTINO),
                createAluno("Maria José", "222BBB", Curso.ADMINISTRACAO, Status.ATIVO, Turno.NOTURNO)
        );

        // Configura o comportamento esperado do mock quando o método findByNomeContainingIgnoreCase() é chamado
        when(alunoRepository.findByNomeContainingIgnoreCase("José")).thenReturn(alunosComJose);

        // Chama o método real que você deseja testar
        List<Aluno> result = alunoRepository.findByNomeContainingIgnoreCase("José");

        // Verifica se o resultado é o esperado
        assertEquals(alunosComJose, result);
    }


   
}