package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "State")
public class State {
	@Id
	@Column(name = "Code")
	private int code;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "UF")
	private String uf;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private List<City> city;

	public State() {
		super();
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}
	
	


	
	
	


	
}
