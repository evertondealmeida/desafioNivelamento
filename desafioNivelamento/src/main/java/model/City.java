package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

	@Column(name = "CodeState")
	private Integer codeState;

	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
	private List<Shop> shop;


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


	public Integer getCodeState() {
		return codeState;
	}

	public void setCodeState(Integer codeState) {
		this.codeState = codeState;
	}

	public void setShop(List<Shop> shop) {
		this.shop = shop;
	}
	
	

}
