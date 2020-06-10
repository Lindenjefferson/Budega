package my.group.Budega.models.DTO;

public class ProdutoDTO {

	private long id;
	
	private String nome;
	private int quantidade;
	private double preco;
	
	private Long categoriaID;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Long getCategoriaID() {
		return categoriaID;
	}

	public void setCategoriaID(Long categoriaID) {
		this.categoriaID = categoriaID;
	}
	
}
