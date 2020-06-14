package my.group.Budega.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.group.Budega.models.Categoria;
import my.group.Budega.models.Produto;
import my.group.Budega.models.DTO.ProdutoDTO;
import my.group.Budega.service.CategoriaService;
import my.group.Budega.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ModelAndView listProdutos() {
		ModelAndView mv = new ModelAndView("Produto/listaProdutos");
		mv.addObject("produtos", produtoService.findAll());
		mv.addObject("categorias", categoriaService.findByNameInOrder());
		ProdutoDTO produtoObj = new ProdutoDTO();
		mv.addObject("produtoObj", produtoObj);
		return mv;
	}

	@GetMapping("/={id}")
	public ModelAndView listOneProduto(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("Produto/listaProdutos");
		mv.addObject("categorias", categoriaService.findByNameInOrder());
		mv.addObject("produtos", produtoService.findAll());
		mv.addObject("produtoObj", produtoDTO(produtoService.findByID(id)));
		return mv;
	}
	
	private ProdutoDTO produtoDTO (Optional<Produto> produto0) {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setId(produto0.get().getId());
		produto.setNome(produto0.get().getNome());
		produto.setPreco(produto0.get().getPreco());
		produto.setQuantidade(produto0.get().getQuantidade());
		produto.setCategoriaNome(produto0.get().getCategoria().getNome());
		return produto;
	}

	@PostMapping("/buscar")
	public ModelAndView buscarProdutos(@RequestParam("busca") String name) {
		ModelAndView mv = new ModelAndView("Produto/listaProdutos");
		mv.addObject("produtos", produtoService.findByNames(name));
		ProdutoDTO produtoObj = new ProdutoDTO();
		mv.addObject("produtoObj", produtoObj);
		return mv;
	}
	
	@PostMapping
	public String saveProduto(ProdutoDTO produtoDTO) {
		Optional<Categoria> categoria = categoriaService.findBySpcName(produtoDTO.getCategoriaNome());
		if (categoria.isPresent()) {
			Produto produto = new Produto(produtoDTO, categoria.get());
			produtoService.save(produto);
			return "redirect:/produto";
		} else {
			return "Categoria não existe";
		}
	}

	@GetMapping("delete/{id}")
	public String deleteProduto(@PathVariable(name = "id") long id) {
		Optional<Produto> produto = produtoService.findByID(id);
		if (produto.isPresent()) {
			produtoService.delete(id);
			return "redirect:/produto";
		} else {
			return "Erro ao deletar";
		}
	}
	
}
