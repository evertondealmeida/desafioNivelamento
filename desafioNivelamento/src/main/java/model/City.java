package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City {
	@Id
	@Column(name = "Code")
	private Integer code;

	@Column(name = "Name")
	private String name;

	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private List<Shop> shop;
	
	@ManyToOne
	@JoinColumn(name="state_Code")
	private State state;


	public City() {
		super();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shop> getShop() {
		return shop;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	
	
	

}
