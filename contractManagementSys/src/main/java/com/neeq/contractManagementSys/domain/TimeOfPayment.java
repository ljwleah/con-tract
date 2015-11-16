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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TimeOfPayment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long contractId;


	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date plannedTimeOfPayment;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")

	private Date actualTimeOfPayment;

	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date alertDate;
	private String content;	
	
	public Date getAlertDate() {
		return alertDate;
	}
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPlannedTimeOfPayment() {
		return plannedTimeOfPayment;
	}
	public void setPlannedTimeOfPayment(Date plannedTimeOfPayment) {
		this.plannedTimeOfPayment = plannedTimeOfPayment;
	}
	public Date getActualTimeOfPayment() {
		return actualTimeOfPayment;
	}
	public void setActualTimeOfPayment(Date actualTimeOfPayment) {
		this.actualTimeOfPayment = actualTimeOfPayment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
}
