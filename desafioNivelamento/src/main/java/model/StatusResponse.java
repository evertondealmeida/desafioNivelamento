package model;

public enum StatusResponse {
	SUCCESS("Sucesso"), ERROR("Erro"),;
	final private String status;

	StatusResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
