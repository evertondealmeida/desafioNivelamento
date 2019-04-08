package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*@Entity
@Table(name = "state")*/
public class State {
	//@Id
	//@Column(name = "code")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int code;
	
	//@Column(name = "name")
	private String name;
	
	//@Column(name = "uf")
	private String uf;
	
	@OneToMany
	private List city;
	
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
	public List getCity() {
		return city;
	}
	public void setCity(List city) {
		this.city = city;
	}
	
	

	
}
