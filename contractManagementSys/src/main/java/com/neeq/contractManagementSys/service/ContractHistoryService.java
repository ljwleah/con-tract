package com.neeq.contractManagementSys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.ContractHistory;
import com.neeq.contractManagementSys.repository.ContractHistoryRepository;

@Service
public class ContractHistoryService {
	@Autowired
	private ContractHistoryRepository cr;
	
	public List<ContractHistory> findByOriginalId(Long originalId){
		return cr.findByOriginalId(originalId);
	}
	
	public ContractHistory save(ContractHistory contractHistory){
		return cr.save(contractHistory);
	}
}
