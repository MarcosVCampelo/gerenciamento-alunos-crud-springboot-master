package br.com.gerenciamento.service;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private PrintService printar;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObterListaAlunos() {
        when(alunoRepository.findAll()).thenReturn(Collections.emptyList());
        List<Aluno> result = alunoService.obterListaAlunos(null);
        verify(alunoRepository, times(1)).findAll();
        assertEquals(Collections.emptyList(), result);
    }

    
    @Test
    void testInserirAlunoComSucesso() {
        Aluno aluno = new Aluno();
        BindingResult bindingResult = mock(BindingResult.class);
    
        // Configurar comportamento do BindingResult
        when(bindingResult.hasErrors()).thenReturn(false);
    
        // Configurar comportamento do mock do repository
        doNothing().when(alunoRepository).save(aluno);
    
        // Chamar o método do serviço
        ModelAndView modelAndView = alunoService.inserirAluno(aluno, bindingResult);
    
        // Verificar se o método do repositório foi chamado
        verify(alunoRepository, times(1)).save(aluno);
    
        // Verificar se o método do serviço de impressão não foi chamado
        verifyNoInteractions(printar);
    
        // Verificar o resultado
        assertEquals("redirect:/alunos-adicionados", modelAndView.getViewName());
    }

@Test
void testInserirAlunoComErro() {
    Aluno aluno = new Aluno();
    BindingResult bindingResult = mock(BindingResult.class);

    // Configurar comportamento do BindingResult
    when(bindingResult.hasErrors()).thenReturn(true);

    // Configurar comportamento do mock do serviço de impressão
    when(printar.modelAndView(anyString(), eq(aluno), anyString())).thenReturn(new ModelAndView("Aluno/formAluno"));

    // Chamar o método do serviço
    ModelAndView modelAndView = alunoService.inserirAluno(aluno, bindingResult);

    // Verificar se o método do repositório não foi chamado
    verifyNoInteractions(alunoRepository);

    // Verificar se o método do serviço de impressão foi chamado
    verify(printar, times(1)).modelAndView(anyString(), eq(aluno), anyString());

    // Verificar o resultado
    assertEquals("Aluno/formAluno", modelAndView.getViewName());
    }
}
