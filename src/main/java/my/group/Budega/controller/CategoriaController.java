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
import my.group.Budega.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ModelAndView listCategorias() {
		ModelAndView mv = new ModelAndView("Categoria/Categoria");
		mv.addObject("categorias", categoriaService.findAll());
		Categoria categoriaObj = new Categoria();
		mv.addObject("categoriaObj", categoriaObj);
		return mv;
	}
	
	@GetMapping("/={id}")
	public ModelAndView listOneCategoria(@PathVariable(name = "id") long id) {
		ModelAndView mv = new ModelAndView("Categoria/Categoria");
		mv.addObject("categorias", categoriaService.findAll());
		Optional<Categoria> categoriaObj = categoriaService.findByID(id);
		mv.addObject("categoriaObj", categoriaObj);
		return mv;
	}
	
	@PostMapping
	public String saveCategoria(Categoria categoria) {
		categoriaService.save(categoria);
		return "redirect:/categoria";
	}
	
	@GetMapping("delete/{id}")
	public String deleteCategoria(@PathVariable(name = "id") long id) {
		Optional<Categoria> categoria = categoriaService.findByID(id);
		if(categoria.isPresent()) {
			categoriaService.delete(id);
			return "redirect:/categoria";
		} else {
			return "Erro ao deletar";
		}
	}

	@PostMapping("/buscar")
	public ModelAndView buscarProdutos(@RequestParam("busca") String name) {
		ModelAndView mv = new ModelAndView("Categoria/Categoria");
		mv.addObject("categorias", categoriaService.findByNames(name));
		Categoria categoriaObj = new Categoria();
		mv.addObject("categoriaObj", categoriaObj);
		return mv;
	}
	
}
