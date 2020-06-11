package my.group.Budega.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import my.group.Budega.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByNomeContainingIgnoreCase(String nome);
	
}
