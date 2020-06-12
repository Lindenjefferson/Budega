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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import my.group.Budega.models.Categoria;
import my.group.Budega.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ModelAndView listCategorias() {
		ModelAndView mv = new ModelAndView("Categoria/editCategoria");
		mv.addObject("categorias", categoriaRepository.findAll());
		return mv;
	}
	
	@PostMapping
	public String saveCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
		return "redirect:/categoria";
	}
	
	@GetMapping("delete/{id}")
	public String deleteCategoria(@PathVariable(name = "id") long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return "redirect:/categoria";
		} else {
			return "Erro ao deletar";
		}
	}
	
	@GetMapping("update/{id}")
	public String updateCategoria(Categoria categoria, @PathVariable(name = "id") long id) {
		Optional<Categoria> categoria0 = categoriaRepository.findById(id);
		if(categoria0.isPresent()) {
			categoria.setId(categoria0.get().getId());
			categoriaRepository.save(categoria);
			return "redirect:/categoria";
		} else {
			return "Erro ao atualizar";
		}
	}
	
	@PostMapping("/buscar")
	public ModelAndView buscarProdutos(@RequestParam("busca") String nome) {
		ModelAndView mv = new ModelAndView("Categoria/editCategoria");
		mv.addObject("categorias", categoriaRepository.findByNomeContainingIgnoreCase(nome));
		return mv;
	}
	
}
