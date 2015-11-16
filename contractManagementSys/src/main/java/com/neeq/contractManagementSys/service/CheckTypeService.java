package com.neeq.contractManagementSys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.CheckType;
import com.neeq.contractManagementSys.repository.CheckTypeRepository;

@Service
public class CheckTypeService {
	@Autowired
	private CheckTypeRepository ctr;
	@Transactional
	public CheckType findByTypeId(Integer typeId){
		return ctr.findByTypId(typeId);
		
	}
	@Transactional
	public List<CheckType> findAll(){
		return ctr.findAll();
		
	}
}
