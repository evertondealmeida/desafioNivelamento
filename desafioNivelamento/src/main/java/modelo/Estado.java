package modelo;

import java.util.List;

public class Estado {
	 private int codigo;
	 private String nome;
	 private String uf;
	 private List cidade;
	 
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
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public List getCidade() {
		return cidade;
	}
	public void setCidade(List cidade) {
		this.cidade = cidade;
	}
	 
	 
	 
}
