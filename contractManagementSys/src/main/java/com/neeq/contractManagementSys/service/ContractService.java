package com.neeq.contractManagementSys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.repository.ContractRepository;

@Service
public class ContractService {
	
	@Autowired
	private ContractRepository cr;
	
	@Transactional
	public Contract save(Contract contract){
		if (contract.getId()==null) {
			
		}
		return cr.save(contract);
	}
	
	@Transactional
	public List<Contract> findAll(){
		return cr.findAll();
	}
	
	@Transactional
	public void delete(Contract contract){
		this.cr.delete(contract);
	}
	@Transactional
	public Contract findById(Long id){
	
		return cr.findOne(id);
	}
	@Transactional
	public List<Contract> findByGroupID(Integer id){
		return cr.searchByGroupId(id);
	}
	@Transactional
	public List<Contract> findByName(String name){
		return cr.searchByOperatorName(name);
	}
	@Transactional
	public List<Contract> findBykey(String key){
	
		return cr.search(key,key);
	}
}
