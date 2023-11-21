package br.com.gerenciamento.method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;


public class ListaTest {

    private Lista lista;
    private AlunoRepository alunoRepositoryMock;

    @BeforeEach
    public void setUp() {
        alunoRepositoryMock = mock(AlunoRepository.class);
        lista = new Lista();
        lista.setAlunoRepository(alunoRepositoryMock);
    }


    @Test
    public void testObterListaAlunosSemNome() {
    
        Aluno aluno1 = new Aluno();
        aluno1.setNome("João");
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria");

        when(alunoRepositoryMock.findAll()).thenReturn(Arrays.asList(aluno1, aluno2));

        List<Aluno> result = lista.obterListaAlunos(null);


        assertEquals(2, result.size());
    }

    @Test
    public void testObterListaAlunosComNome() {

        Aluno aluno1 = new Aluno();
        aluno1.setNome("João");
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria");

        when(alunoRepositoryMock.findByNomeContainingIgnoreCase("João")).thenReturn(Arrays.asList(aluno1));

        List<Aluno> result = lista.obterListaAlunos("João");

        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
    }
}

