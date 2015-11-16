package com.neeq.contractManagementSys.service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.Check;
import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.repository.RenewalRepository;

@Service
public class RenewalService {
	@Autowired
	private RenewalRepository rr;
	
	@Transactional
	public List<Renewal> findByContractId(Long contractId){
		return rr.findByContractId(contractId);
	}
	
	@Transactional
	public List<Renewal> findByAlertDate(Date alertDate){
		return rr.findByAlertDate(alertDate);
	}
	
	@Transactional
	public Renewal save(Renewal renewal){
		return rr.save(renewal);
	}
	
	@Transactional
	public void delete(Renewal renewal) {
		this.rr.delete(renewal);
	}
	
	@Transactional
	public List<Renewal> findAll() {
		return rr.findAll();
	}
}
