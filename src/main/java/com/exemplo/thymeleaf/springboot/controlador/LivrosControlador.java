package com.exemplo.thymeleaf.springboot.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.exemplo.thymeleaf.springboot.excecao.LivrosNaoEncontradaExcecao;
import com.exemplo.thymeleaf.springboot.modelo.Livros;
import com.exemplo.thymeleaf.springboot.servico.ILivrosServico;

@Controller
@RequestMapping("/livros")
public class LivrosControlador {

    @Autowired
    private ILivrosServico service;

    @GetMapping("/")
    public String exibirPaginaInicial() {
        return "paginaInicio";
    }

    @GetMapping("/adicionar")
    public String exibirFormularioAdicao() {
        return "adicionarLivros";
    }

    @PostMapping("/salvar")
    public String salvarLivros(@ModelAttribute Livros livros, Model modelo) {
        service.salvarLivros(livros);
        Long id = service.salvarLivros(livros).getId();
        String mensagem = "Livro com id: '" + id + "' salva com sucesso!";
        modelo.addAttribute("message", mensagem);
        return "adicionarLivros";
    }

    @GetMapping("/listar")
    public String buscarTodasLivros(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
        List<Livros> livros = service.buscarTodasLivros();
        modelo.addAttribute("listagem", livros);
        modelo.addAttribute("message", mensagem);
        return "listarLivros";
    }

    @GetMapping("/editar")
    public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long id) {
        String pagina = null;
        try {
            Livros livros = service.bucarLivrosPorId(id);
            modelo.addAttribute("livros", livros);
            pagina = "editarLivros";
        } catch (LivrosNaoEncontradaExcecao e) {
            e.printStackTrace();
            atributos.addAttribute("message", e.getMessage());
            pagina = "redirect:listar";
        }
        return pagina;
    }

    @PostMapping("/atualizar")
    public String atualizarLivros(@ModelAttribute Livros livros, RedirectAttributes atributos) {
        service.atualizarLivros(livros);
        Long id = livros.getId();
        atributos.addAttribute("message", "Livro com id: '" + id + "' atualizado com sucesso!");
        return "redirect:listar";
    }

    @GetMapping("/deletar")
    public String deletarLivros(@RequestParam Long id, RedirectAttributes atributos) {
        try {
            service.deletarLivrosPorId(id);
            atributos.addAttribute("message", "Livro com id: '" + id + "' excluído com sucesso!");
        } catch (LivrosNaoEncontradaExcecao e) {
            e.printStackTrace();
            atributos.addAttribute("message", e.getMessage());
        }
        return "redirect:listar";
    }
}