package br.com.gerenciamento.controller;

import br.com.gerenciamento.method.Printar;
import br.com.gerenciamento.repository.AlunoRepository;
import br.com.gerenciamento.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    private Printar printar = new Printar();


    @GetMapping("/inserirAlunos")
    public ModelAndView insertAlunos(Aluno aluno) {
        return printar.modelAndView("Aluno/formAluno", new Aluno(), "aluno");
    }

    @PostMapping("InsertAlunos")
    public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return printar.modelAndView("Aluno/formAluno", aluno, "aluno");
        } else {
            alunoRepository.save(aluno);
            return printar.modelAndView("redirect:/alunos-adicionados", aluno, "aluno");
        }
    }

    @GetMapping("alunos-adicionados")
    public ModelAndView listagemAlunos() {
        return printar.modelAndView("Aluno/listAlunos", alunoRepository.findAll(), "alunosList");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Aluno aluno = alunoRepository.getById(id);
        return printar.modelAndView("Aluno/editar", aluno, "aluno");
    }

    @PostMapping("/editar")
    public ModelAndView editar(Aluno aluno) {
        alunoRepository.save(aluno);
        return printar.modelAndView("redirect:/alunos-adicionados", aluno, "aluno");
    }

    @GetMapping("/remover/{id}")
    public String removerAluno(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("filtro-alunos")
    public ModelAndView filtroAlunos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Aluno/filtroAlunos");
        return modelAndView;
    }

    @GetMapping("alunos-ativos")
    public ModelAndView listaAlunosAtivos() {
        return printar.modelAndView("Aluno/alunos-ativos", alunoRepository.findByStatusAtivo(), "alunosAtivos");
    }

    @GetMapping("alunos-inativos")
    public ModelAndView listaAlunosInativos() {
        return printar.modelAndView("Aluno/alunos-inativos", alunoRepository.findByStatusInativo(), "alunosInativos");
    }

    @PostMapping("/pesquisar-aluno")
    public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
        List<Aluno> listaAlunos;
        if (nome == null || nome.trim().isEmpty()) {
            listaAlunos = alunoRepository.findAll();
        } else {
            listaAlunos = alunoRepository.findByNomeContainingIgnoreCase(nome);
        }
        return printar.modelAndView("Aluno/pesquisa-resultado", listaAlunos, "ListaDeAlunos");
    }
}