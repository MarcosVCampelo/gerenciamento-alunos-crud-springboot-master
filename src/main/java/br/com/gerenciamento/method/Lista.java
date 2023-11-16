package br.com.gerenciamento.method;

import java.util.List; // Importa a classe correta
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.repository.AlunoRepository;

public class Lista {

    private AlunoRepository alunoRepository;

    public List<Aluno> obterListaAlunos(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return alunoRepository.findAll();
        } else {
            return alunoRepository.findByNomeContainingIgnoreCase(nome);
        }
    }
}