package com.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


//The @Entity annotation is used to mark this class as an Entity bean.
@Entity
//The @Table annotation is used to specify the table to persist the data.
@Table
public class Client {

	@Id	//(The @Idannotation is inherited from javax.persistence.Id，
	//indicating the member field below is the primary key of current entity.)
	@GeneratedValue //(@GeneratedValue annotation, which the increase type for auto increment column)
	private int clientId;
	private String account;
	
	@NotEmpty(message="Cannot be empty")
	@Pattern(regexp="[a-zA-Z\\s]+$", message="Enter only Letters")
	private String clientName;
	
	@NotEmpty(message="Cannot be empty")
	private String projectName;
	
	@Override	//(by using this annotation you instruct compiler that you are overriding this method. )
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName
				+ ", projectName=" + projectName + ", arrivalDate="
				+ arrivalDate + ", deptDate=" + deptDate + ", agenda=" + agenda
				+ ", eventData=" + eventData + "]";
	}
	@NotNull
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date arrivalDate;
	
	@NotNull
	@Future
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deptDate;
	
	@NotEmpty(message="Cannot be empty")
	@Pattern(regexp="[a-zA-Z\\s]+$", message="Enter only Letters")
	private String agenda;
	
	@Transient
	//The @Transient annotation tells the JPA provider to not persist any (non-transient) attribute.
	private String eventData;
	
	@Transient
	private String stringarrive;
	@Transient
	private String stringdepart;
	
	
	
	public String getStringarrive() {
		return stringarrive;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setStringarrive(String stringarrive) {
		this.stringarrive = stringarrive;
	}
	public String getStringdepart() {
		return stringdepart;
	}
	public void setStringdepart(String stringdepart) {
		this.stringdepart = stringdepart;
	}

	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getDeptDate() {
		return deptDate;
	}
	public void setDeptDate(Date deptDate) {
		this.deptDate = deptDate;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public String getEventData() {
		return eventData;
	}
	public void setEventData(String eventData) {
		this.eventData = eventData;
	}
	
	
}
