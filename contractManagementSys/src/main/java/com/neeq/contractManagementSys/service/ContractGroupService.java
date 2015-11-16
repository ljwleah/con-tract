package com.neeq.contractManagementSys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.ContractGroup;
import com.neeq.contractManagementSys.repository.ContractGroupRepository;

@Service
public class ContractGroupService {
	@Autowired
	private ContractGroupRepository cgr;
	@Transactional
	public ContractGroup findByGroupId(Integer integer){
		return cgr.findByGroupId(integer);
	}
	@Transactional
	public List<ContractGroup> findAll(){
		return cgr.findAll();
	}
}
