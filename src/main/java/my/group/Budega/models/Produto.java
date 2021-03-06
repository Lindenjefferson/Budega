package my.group.Budega.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import my.group.Budega.models.DTO.ProdutoDTO;

@Data
@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String nome;
	private int quantidade;
	private double preco;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id", nullable = false)
	private Categoria categoria;

	public Produto(ProdutoDTO dto, Categoria categoria) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.quantidade = dto.getQuantidade();
        this.preco = dto.getPreco();
        this.categoria = categoria;
    }
	
	public Produto() {}

}
