package my.group.Budega.models.DTO;

import lombok.Data;

@Data
public class ProdutoDTO {

	private long id;
	
	private String nome;
	private int quantidade;
	private double preco;
	
	private String categoriaNome;

}
