package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Log")
public class Log {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Local")
	private String local;
	
	@Column(name = "TypeMessage")
	private String typeMessage;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "DateLog")
	private String dateLog;
	
	@Transient
	private String lineColor;

	
	public Log() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(String typeMessage) {
		this.typeMessage = typeMessage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateLog() {
		return dateLog;
	}

	public void setDateLog(String dateLog) {
		this.dateLog = dateLog;
	}
	
	
	
	public String getLineColor() {
		return lineColor;
	}

	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}

	public void DateFormated() {	
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");		   
		setDateLog(out.format(new Date()));	
	}
	
	
}