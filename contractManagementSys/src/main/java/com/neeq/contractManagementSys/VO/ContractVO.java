package com.neeq.contractManagementSys.VO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.neeq.contractManagementSys.domain.Contract;

public class ContractVO implements Serializable{

	@Id
	private Long id;
	@NotNull(message="组别不能为空")
	private Integer groupId;
	private String contractId;
	@NotBlank(message="合同名称不能为空")
	private String name;
	private Long type;
	private Long state;
	private String content;
	//@NotNull(message="金额不能为空")
	@NumberFormat(style =  Style.CURRENCY)
	private String total;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull(message="签订日期不能为空")
	private Date signDate;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull(message="起始日期不能为空")
	private Date startingDate;
	
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	//@Min()
	private Date endDate;
	private String partyB;
	@NotBlank(message="合同概要不能为空")
	private String summary;
	private String validPeriod;
	private String contractDocumentPath;
	private String remark;
	private Long parentContractId;
	
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	@NotNull(message="经办开始日期不能为空")
	private Date operatorStartDate;
	@Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
	private Date operatorEndDate;
	/*@NotBlank(message="经办人不能为空")
	private String operatorName;
	@NotBlank(message="经办人邮箱不能为空")
	@Email
	private String email;*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
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
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
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
	
	public Contract transToContract(){
		Contract ctt = new Contract();
		BeanUtils.copyProperties(this, ctt);
		if(this.getTotal() == null || StringUtil.isEmpty(this.getTotal())){
			ctt.setTotal(0.0);
		}
		else ctt.setTotal(Double.parseDouble(this.getTotal()));
		if(this.getValidPeriod() == null || StringUtil.isEmpty(this.getValidPeriod())){
			ctt.setValidPeriod(0);
		}
		else ctt.setValidPeriod(Integer.valueOf(this.getValidPeriod()));
		return ctt;
	}
}
