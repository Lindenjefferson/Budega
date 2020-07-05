package my.group.Budega.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.group.Budega.models.Produto;
import my.group.Budega.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> findByID(long id) {
		return produtoRepository.findById(id);
	}
	
	public List<Produto> findByNames(String name) {
		return produtoRepository.findByNomeContainingIgnoreCase(name);
	}
	
	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void delete(long id) {
		produtoRepository.deleteById(id);
	}
	
}
