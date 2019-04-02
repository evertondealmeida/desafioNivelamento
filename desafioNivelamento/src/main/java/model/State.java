package model;

import java.util.List;

public class State {
	private int code;
	private String name;
	private String uf;
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
