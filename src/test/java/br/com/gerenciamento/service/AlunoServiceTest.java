package br.com.gerenciamento.service;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;

public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private PrintService printService;

    @InjectMocks
    private AlunoService alunoService;

      @BeforeEach
    public void setUp() {
        alunoRepository = mock(AlunoRepository.class);
        printService = mock(PrintService.class);
        alunoService = new AlunoService(alunoRepository, printService);
    }
    private Aluno criarAlunoExemplo() {
        Aluno aluno = new Aluno();
        aluno.setNome("Exemplo Aluno");
        aluno.setMatricula("123");
        aluno.setCurso(Curso.ADMINISTRACAO);
        aluno.setTurno(Turno.MATUTINO);
        aluno.setStatus(Status.ATIVO);
        return aluno;
    }
        

    @Test
    public void testInserirAluno() {
        Aluno aluno = criarAlunoExemplo();
        BindingResult bindingResultMock = mock(BindingResult.class);
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        when(printService.modelAndView("redirect:/alunos-adicionados", aluno, "aluno"))
                .thenReturn(new ModelAndView("redirect:/alunos-adicionados"));
        ModelAndView result = alunoService.inserirAluno(aluno, bindingResultMock);
        verify(alunoRepository, times(1)).save(aluno);
        verify(printService, times(1)).modelAndView(eq("redirect:/alunos-adicionados"), eq(aluno), eq("aluno"));
        assertNotNull(result);
        assertEquals("redirect:/alunos-adicionados", result.getViewName());
    }
    @Test
    void testObterListaAlunos() {
        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());
        List<Aluno> result = alunoService.obterListaAlunos(null);
        verify(alunoRepository, times(1)).findAll();
        assertEquals(Collections.emptyList(), result);
    }



}

