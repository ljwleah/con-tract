package com.neeq.contractManagementSys.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.Check;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.repository.CheckRepository;

@Service
public class CheckService {
	@Autowired
	private CheckRepository cr;
	
	@Transactional
	public List<Check> findByContractId(Long contractId){
		return cr.findByContractId(contractId);
	}
	
	@Transactional
	public List<Check> findByAlertDate(Date alertDate){
		return cr.findByAlertDate(alertDate);
	}
	
	@Transactional
	public Check save(Check check){
		return cr.save(check);
	}
	
	@Transactional
	public void delete(Check check){
		this.cr.delete(check);
	}
	
	@Transactional
	public List<Check> findAll() {
		return cr.findAll();
	}
}
