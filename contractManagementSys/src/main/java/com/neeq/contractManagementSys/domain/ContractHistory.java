package com.neeq.contractManagementSys.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ContractHistory implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long originalId;
	private Integer groupId;
	private String contractId;
	private String name;
	private Long type;
	private Long state;
	private String content;
	private double total;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date signDate;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date startingDate;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date endDate;
	private String partyB;
	private String summary;
	private int validPeriod;
	private String contractDocumentPath;
	private String remark;
	private Long parentContractId;
	private String operatorName;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date operatorStartDate;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date operatorEndDate;
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOriginalId() {
		return originalId;
	}
	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPartyB() {
		return partyB;
	}
	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}
	public String getContractDocumentPath() {
		return contractDocumentPath;
	}
	public void setContractDocumentPath(String contractDocumentPath) {
		this.contractDocumentPath = contractDocumentPath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getParentContractId() {
		return parentContractId;
	}
	public void setParentContractId(Long parentContractId) {
		this.parentContractId = parentContractId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Date getOperatorStartDate() {
		return operatorStartDate;
	}
	public void setOperatorStartDate(Date operatorStartDate) {
		this.operatorStartDate = operatorStartDate;
	}
	public Date getOperatorEndDate() {
		return operatorEndDate;
	}
	public void setOperatorEndDate(Date operatorEndDate) {
		this.operatorEndDate = operatorEndDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
