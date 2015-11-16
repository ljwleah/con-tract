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
public class Renewal implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long contractId;

	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date plannedDateOfRenewal;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date actualDateOfRenewal;
	private String content;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date alertDate;
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
	public Date getPlannedDateOfRenewal() {
		return plannedDateOfRenewal;
	}
	public void setPlannedDateOfRenewal(Date plannedDateOfRenewal) {
		this.plannedDateOfRenewal = plannedDateOfRenewal;
	}
	public Date getActualDateOfRenewal() {
		return actualDateOfRenewal;
	}
	public void setActualDateOfRenewal(Date actualDateOfRenewal) {
		this.actualDateOfRenewal = actualDateOfRenewal;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}
}
