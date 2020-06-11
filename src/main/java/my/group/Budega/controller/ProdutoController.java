package my.group.Budega.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.group.Budega.models.Categoria;
import my.group.Budega.models.Produto;
import my.group.Budega.models.DTO.ProdutoDTO;
import my.group.Budega.repository.CategoriaRepository;
import my.group.Budega.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping
	public ModelAndView listProdutos() {
		ModelAndView mv = new ModelAndView("listaProdutos");
		mv.addObject("produtos", produtoRepository.findAll());
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView listOneProduto(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("addProdutos");
		mv.addObject("produto", produtoRepository.findById(id));
		return mv;
	}
	
	@GetMapping("/add")
	public ModelAndView addProduto() {
		ModelAndView mv = new ModelAndView("addProdutos");
		return mv;
	}
	
	@GetMapping("/{nome}")
	public ModelAndView buscarProdutos(@PathVariable(name = "nome") String nome) {
		ModelAndView mv = new ModelAndView("listaProdutos");
		mv.addObject("produtos", produtoRepository.findByNomeContainingIgnoreCase(nome));
		return mv;
	}
	
	@PostMapping
	public String saveProduto(ProdutoDTO produtoDTO) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(produtoDTO.getCategoriaNome());
		if (categoria.isPresent()) {
			Produto produto = new Produto(produtoDTO, categoria.get());
			produtoRepository.save(produto);
			return "redirect:/produto";
		} else {
			return "Erro ao salvar";
		}
	}

	@DeleteMapping("/{id}")
	public String deleteProduto(@PathVariable(name = "id") long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produtoRepository.deleteById(id);
			return "redirect:/produto";
		} else {
			return "Erro ao deletar";
		}
	}

	@PutMapping("/add/{id}")
	public String updateProduto(ProdutoDTO produtoDTO, @PathVariable(name = "id") long id) {
		Optional<Categoria> categoria = categoriaRepository.findByNome(produtoDTO.getCategoriaNome());
		Optional<Produto> produto0 = produtoRepository.findById(id);
		if (produto0.isPresent() && categoria.isPresent()) {
			Produto produto = new Produto(produtoDTO, categoria.get());
			produto.setId(produto0.get().getId());
			produtoRepository.save(produto);
			return "redirect:/produto";
		} else {
			return "Erro ao salvar";
		}
	}
	
}
