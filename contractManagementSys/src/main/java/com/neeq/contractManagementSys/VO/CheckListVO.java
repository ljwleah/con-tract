package com.neeq.contractManagementSys.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CheckListVO {
	private Long id;
	private Long contractId;
	private String typeName;
	private Date plannedDateOfCheck;
	private Date actualDateOfCheck;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
