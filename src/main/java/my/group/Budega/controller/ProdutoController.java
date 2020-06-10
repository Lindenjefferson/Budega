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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import my.group.Budega.models.Produto;
import my.group.Budega.repository.CategoriaRepository;
import my.group.Budega.repository.ProdutoRepository;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/produto")
	public ResponseEntity<List<Produto>> getAllProdutos() {
		List<Produto> produtoList = produtoRepository.findAll();
		if (produtoList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Produto>>(produtoList, HttpStatus.OK);
		}
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> getOneProduto(@PathVariable(value = "id") long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/produto")
	public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
		return new ResponseEntity<Produto>(produtoRepository.save(produto), HttpStatus.OK);
	}

	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable(value = "id") long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produtoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/produto/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable(value = "id") long id, @RequestBody Produto produto) {
		Optional<Produto> produto0 = produtoRepository.findById(id);
		if (produto0.isPresent()) {
			produto.setId(produto0.get().getId());
			return new ResponseEntity<Produto>(produtoRepository.save(produto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
