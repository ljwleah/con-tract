package com.neeq.contractManagementSys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neeq.contractManagementSys.domain.ContractType;
import com.neeq.contractManagementSys.repository.ContractTypeRepository;

@Service
public class ContractTypeService {
	@Autowired
	private ContractTypeRepository ctr;
	
	@Transactional
	public ContractType findByTypeId(long typeId){
		return ctr.findByTypeId(typeId);
	}
	@Transactional
	public List<ContractType> findAll(){
		return ctr.findAll();
	}
}
