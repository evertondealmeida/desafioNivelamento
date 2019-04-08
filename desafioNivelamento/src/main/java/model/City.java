package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {
	@Id
	@Column(name = "code")
	private int code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "codeState")
	private int codeState;

	public City() {
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

	public int getCodeState() {
		return codeState;
	}

	public void setCodeState(int codeState) {
		this.codeState = codeState;
	}

}
