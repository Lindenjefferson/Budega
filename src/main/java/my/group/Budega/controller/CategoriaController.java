package my.group.Budega.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import my.group.Budega.models.Categoria;
import my.group.Budega.repository.CategoriaRepository;

@RestController
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> getAllCategorias() {
		List<Categoria> categoriaList = categoriaRepository.findAll();
		if (categoriaList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Categoria>>(categoriaList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable(value = "id") long id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			return new ResponseEntity<Categoria>(categoria.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria){
		return new ResponseEntity<Categoria>(categoriaRepository.save(categoria), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable(value="id") long id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/categoria/{id}")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable(value="id") long id, @RequestBody Categoria categoria){
		Optional<Categoria> categoria0 = categoriaRepository.findById(id);
		if (categoria0.isPresent()) {
			categoria.setId(categoria0.get().getId());
			return new ResponseEntity<Categoria>(categoriaRepository.save(categoria), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
