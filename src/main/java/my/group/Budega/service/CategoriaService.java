package my.group.Budega.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.group.Budega.models.Categoria;
import my.group.Budega.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> findByID(long id) {
		return categoriaRepository.findById(id);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void delete(long id) {
		categoriaRepository.deleteById(id);
	}
	
	public List<Categoria> findByNames(String name) {
		return categoriaRepository.findByNomeContainingIgnoreCase(name);
	}
	
	public List<Categoria> findByNameInOrder() {
		return categoriaRepository.findAllByOrderByNomeAsc();
	}
	
	public Optional<Categoria> findBySpcName(String name) {
		return categoriaRepository.findByNome(name);
	}
	
}
