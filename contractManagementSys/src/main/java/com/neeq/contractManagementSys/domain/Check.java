package com.neeq.contractManagementSys.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Check implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private Long contractId;
	private int type;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date plannedDateOfCheck;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date actualDateOfCheck;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date alertDate;
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getPlannedDateOfCheck() {
		return plannedDateOfCheck;
	}
	public void setPlannedDateOfCheck(Date plannedDateOfCheck) {
		this.plannedDateOfCheck = plannedDateOfCheck;
	}
	public Date getActualDateOfCheck() {
		return actualDateOfCheck;
	}
	public void setActualDateOfCheck(Date actualDateOfCheck) {
		this.actualDateOfCheck = actualDateOfCheck;
	}
	public Date getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
