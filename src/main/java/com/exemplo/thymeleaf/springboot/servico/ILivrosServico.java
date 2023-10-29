
package com.exemplo.thymeleaf.springboot.servico;

import java.util.List;
import com.exemplo.thymeleaf.springboot.modelo.Livros;

public interface ILivrosServico {

    public Livros salvarLivros(Livros livros);

    public List<Livros> buscarTodasLivros();

    public Livros bucarLivrosPorId(Long id);

    public void deletarLivrosPorId(Long id);

    public void atualizarLivros(Livros livros);

}