package my.group.Budega.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.group.Budega.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByNome(String nome);
	List<Categoria> findAllByOrderByNomeAsc();
	List<Categoria> findByNomeContainingIgnoreCase(String nome);
	
}
