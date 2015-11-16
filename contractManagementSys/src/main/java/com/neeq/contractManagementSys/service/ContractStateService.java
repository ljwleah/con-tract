package com.neeq.contractManagementSys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.ContractState;
import com.neeq.contractManagementSys.repository.ContractStateRepository;

@Service
public class ContractStateService {
	@Autowired
	private ContractStateRepository csr;
	
	@Transactional
	public ContractState findByStateId(Long stateId){
		return csr.findByStateId(stateId);
	}
	@Transactional
	public List<ContractState> findAll(){
		return csr.findAll();
	}
}
