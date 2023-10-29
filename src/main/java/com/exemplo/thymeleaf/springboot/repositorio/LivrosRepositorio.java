package com.exemplo.thymeleaf.springboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.thymeleaf.springboot.modelo.Livros;

public interface LivrosRepositorio extends JpaRepository<Livros, Long> {

}