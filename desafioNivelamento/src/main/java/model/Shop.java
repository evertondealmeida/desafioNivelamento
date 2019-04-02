package model;

public class Shop {
	private Integer id;
	private String name;
	private String address;
	private String phone;
	private String cnpj;
	private String workingHour;
	private Integer codeCity;

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

	public Integer getCodeCity() {
		return codeCity;
	}

	public void setCodeCity(Integer codeCity) {
		this.codeCity = codeCity;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", Name=" + name + ", address=" + address + ", phone=" + phone + ", cnpj="
				+ cnpj + ", workingHour=" + workingHour + ", codeCity=" + codeCity + "]";
	}
	
	public boolean nullFields(Shop Shop) {
		boolean validFields = false;
			if (Shop.getCnpj() != null && 
				Shop.getPhone() != null && 
				Shop.getAddress() != null && 
				Shop.getName() != null && 
				Shop.getCodeCity() != null
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
