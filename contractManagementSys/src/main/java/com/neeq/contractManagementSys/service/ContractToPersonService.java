package com.neeq.contractManagementSys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.neeq.contractManagementSys.domain.ContractToPerson;
import com.neeq.contractManagementSys.repository.ContractToPersonRepository;

public class ContractToPersonService {
	@Autowired
	ContractToPersonRepository cr;
	
	@Transactional
	public ContractToPerson save(ContractToPerson contractToPerson){
		return cr.save(contractToPerson);
	}
	
	@Transactional
	public ContractToPerson findByContractIdAndState(Long contractId, int state){
		return cr.findByContractIdAndState(contractId, state);		
	}
}
