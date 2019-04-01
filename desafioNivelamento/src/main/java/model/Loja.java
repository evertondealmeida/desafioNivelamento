package model;

public class Loja {
	private int id;
	private String nome;
	private String endereco;
	private String telefone;
	private String cnpj;
	private String horarioAtendimento;
	private Integer codigoCidade;
	
	
	public Loja() {
		super();
	}



	/*public Loja(int id, String nome, String endereco, String telefone, String cnpj, String horarioAtendimento,
			int codigoCidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cnpj = cnpj;
		this.horarioAtendimento = horarioAtendimento;
		this.codigoCidade = codigoCidade;
	}*/
	
	

	@Override
	public String toString() {
		return "Loja [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cnpj="
				+ cnpj + ", horarioAtendimento=" + horarioAtendimento + ", codigoCidade=" + codigoCidade + "]";
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public Integer getCodigoCidade() {
		return codigoCidade;
	}

	public void setCodigoCidade(Integer codigoCidade) {
		this.codigoCidade = codigoCidade;
	}

}
