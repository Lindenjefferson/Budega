package my.group.Budega.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.group.Budega.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByNome(String nome);
	
}
