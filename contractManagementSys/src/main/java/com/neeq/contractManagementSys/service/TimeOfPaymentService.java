package com.neeq.contractManagementSys.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.Check;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.domain.TimeOfPayment;
import com.neeq.contractManagementSys.repository.TimeOfPaymentRepository;

@Service
public class TimeOfPaymentService {
	@Autowired
	private TimeOfPaymentRepository tr;
	
	@Transactional
	public List<TimeOfPayment> findByContractId(Long contractId) {
		return tr.findByContractId(contractId);
	}
	
	@Transactional
	public List<TimeOfPayment> findByAlertDate(Date alertDate){
		return tr.findByAlertDate(alertDate);
	}
	
	@Transactional
	public TimeOfPayment save(TimeOfPayment timeOfPayment){
		return tr.save(timeOfPayment);
	}
	
	@Transactional
	public void delete(TimeOfPayment timeOfPayment) {
		this.tr.delete(timeOfPayment);
	}
	
	@Transactional
	public List<TimeOfPayment> findAll() {
		return tr.findAll();
	}
}
