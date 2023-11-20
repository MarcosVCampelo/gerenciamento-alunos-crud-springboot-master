package br.com.gerenciamento.service;

import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;


@Service
public class ServiceAluno {

    private final AlunoRepository alunoRepository;
    @Autowired
    private final Printar printar;


    public ServiceAluno(AlunoRepository alunoRepository, Printar printar) {
        this.alunoRepository = alunoRepository;
        this.printar = printar;
    }

    public ModelAndView inserirAluno(Aluno aluno, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return printar.modelAndView("Aluno/formAluno", aluno, "aluno");
        }
        alunoRepository.save(aluno);
        return printar.modelAndView("redirect:/alunos-adicionados", aluno, "aluno");
    }
}
