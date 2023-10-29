package com.exemplo.thymeleaf.springboot.servico.implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exemplo.thymeleaf.springboot.excecao.LivrosNaoEncontradaExcecao;
import com.exemplo.thymeleaf.springboot.modelo.Livros;
import com.exemplo.thymeleaf.springboot.repositorio.LivrosRepositorio;
import com.exemplo.thymeleaf.springboot.servico.ILivrosServico;

@Service
public class LivrosServicoImpl implements ILivrosServico {

    @Autowired
    private LivrosRepositorio repositorio;

    @Override
    public Livros salvarLivros(Livros livros) {
        return repositorio.save(livros);
    }

    @Override
    public List<Livros> buscarTodasLivros() {
        return repositorio.findAll();
    }

    @Override
    public Livros bucarLivrosPorId(Long id) {
        Optional<Livros> opcional = repositorio.findById(id);
        if (opcional.isPresent()) {
            return opcional.get();
        } else {
            throw new LivrosNaoEncontradaExcecao("Livro com id: " + id + " não encontrada.");
        }
    }

    @Override
    public void deletarLivrosPorId(Long id) {
        repositorio.delete(bucarLivrosPorId(id));
    }

    @Override
    public void atualizarLivros(Livros invoice) {
        repositorio.save(invoice);
    }
}