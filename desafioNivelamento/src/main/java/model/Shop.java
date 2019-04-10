package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shop")
public class Shop {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "workingHour")
	private String workingHour;
	
	@ManyToOne
	@JoinColumn(name="city_Code")
	private City city;

	public Shop() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getWorkingHour() {
		return workingHour;
	}

	public void setWorkingHour(String workingHour) {
		this.workingHour = workingHour;
	}

	
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", Name=" + name + ", address=" + address + ", phone=" + phone + ", cnpj="
				+ cnpj + ", workingHour=" + workingHour + ", codeCity=" +"]";
	}
	
	public boolean nullFields(Shop Shop) {
		boolean validFields = false;
			if (Shop.getCnpj() != null && 
				Shop.getPhone() != null && 
				Shop.getAddress() != null && 
				Shop.getName() != null &&
				Shop.getCity() != null &&
				Shop.getCity().getCode() != null &&
				Shop.getCity().getName() != null &&
				Shop.getCity().getCodeState() != null
			   ) validFields = true;
		return validFields;
	}
	public boolean idNullField(Shop Shop) {
		boolean validFields = false;
			if (Shop.getId() != null && nullFields(Shop)) validFields = true;
		return validFields;
	}
	
	public boolean checkCNPJ(Shop shop) {
		return shop.getCnpj().length() == 14 ? true : false;
	}
	
	public String onlyNumbers(String number) {
		StringBuffer sb = new StringBuffer();
		char[] caracteres = number.toCharArray();
		for (Character caracter : caracteres) {
			if (Character.isDigit(caracter)) {
				sb.append(caracter);
			}
		}
		return sb.toString();
	}
}
