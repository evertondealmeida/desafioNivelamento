package model;




public class Cidade {
	 private int codigo;
	 private String nome;
	 private int codigoEstado;
	 
	
	public Cidade() {
		super();
	}
	public Cidade(int codigo, String nome, int codigoEstado) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.codigoEstado = codigoEstado;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigoEstado() {
		return codigoEstado;
	}
	public void setCodigoEstado(int codigoEstado) {
		this.codigoEstado = codigoEstado;
	} 
	 
	 

}
