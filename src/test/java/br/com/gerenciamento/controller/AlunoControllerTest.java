package br.com.gerenciamento.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;
import br.com.gerenciamento.service.AlunoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AlunoService alunoService;

    @InjectMocks
    private AlunoController alunoController;

    private MockMvc mockMvc;

    @Test
    void testInsertAlunos() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/inserirAlunos"))
                .andExpect(status().isOk())
                .andExpect(view().name("Aluno/formAluno"))
                .andExpect(model().attributeExists("aluno"));
    }

    @Test
    void testInserirAluno() {
        Aluno aluno = new Aluno();
        BindingResult bindingResult = mock(BindingResult.class);
        when(alunoService.inserirAluno(aluno, bindingResult)).thenReturn(new ModelAndView("success"));

        ModelAndView result = alunoController.inserirAluno(aluno, bindingResult);

        assertEquals("success", result.getViewName());
        verify(alunoService, times(1)).inserirAluno(aluno, bindingResult);
    }

    @Test
    void testListagemAlunos() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/alunos-adicionados"))
        .andExpect(view().name("Aluno/listAlunos"));
    }

    @Test
    void testEditar() throws Exception {
        Long alunoId = 1L;
        Aluno sampleAluno = new Aluno();
        sampleAluno.setId(alunoId);
        when(alunoRepository.getById(alunoId)).thenReturn(sampleAluno);
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/editar/{id}", alunoId))
                .andExpect(status().isOk())
                .andExpect(view().name("Aluno/editar"))
                .andExpect(model().attribute("aluno", sampleAluno));
        verify(alunoRepository, times(1)).getById(alunoId);
    }

    @Test
    void testRemoverAluno() throws Exception {
        Long alunoId = 1L;
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/remover/{id}", alunoId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alunos-adicionados"));
        verify(alunoRepository, times(1)).deleteById(alunoId);
    }

    @Test
    void testFiltroAlunos() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/filtro-alunos"))
                .andExpect(status().isOk())
                .andExpect(view().name("Aluno/filtroAlunos"));
    }

    @Test 
    void testListaAlunosInativos() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/alunos-inativos"))
        .andExpect(status().isOk())
        .andExpect(view().name("Aluno/alunos-inativos"));
    }

    @Test 
    void testListaAlunosAtivos() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
        mockMvc.perform(get("/alunos-ativos"))
        .andExpect(status().isOk())
        .andExpect(view().name("Aluno/alunos-ativos"));
    }
}