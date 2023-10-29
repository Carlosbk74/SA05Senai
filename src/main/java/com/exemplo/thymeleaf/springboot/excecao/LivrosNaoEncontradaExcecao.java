package com.exemplo.thymeleaf.springboot.excecao;

public class LivrosNaoEncontradaExcecao extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LivrosNaoEncontradaExcecao() {
        super();
    }

    public LivrosNaoEncontradaExcecao(String mensagemPersonalizada) {
        super(mensagemPersonalizada);
    }
}