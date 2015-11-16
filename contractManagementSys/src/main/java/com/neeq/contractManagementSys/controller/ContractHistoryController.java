package com.neeq.contractManagementSys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neeq.contractManagementSys.VO.ContractListVO;
import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.domain.ContractGroup;
import com.neeq.contractManagementSys.domain.ContractHistory;
import com.neeq.contractManagementSys.domain.ContractState;
import com.neeq.contractManagementSys.domain.ContractType;
import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.service.ContractGroupService;
import com.neeq.contractManagementSys.service.ContractHistoryService;
import com.neeq.contractManagementSys.service.ContractService;
import com.neeq.contractManagementSys.service.ContractStateService;
import com.neeq.contractManagementSys.service.ContractTypeService;
import com.neeq.contractManagementSys.service.PersonService;

@Controller
@RequestMapping("/contractHistory/")
public class ContractHistoryController {
	@Autowired
	private ContractHistoryService contractHistoryService;
	@Autowired
	private ContractStateService contractStateService;
	@Autowired
	private ContractGroupService contractGroupService;
	@Autowired
	private ContractTypeService contractTypeService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private PersonService personService;
	@RequestMapping("list")
	@Transactional(readOnly = true)
	public ModelAndView listByOriginalId(Long originalId, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		List<ContractHistory> contractHistories = this.contractHistoryService.findByOriginalId(originalId);
		List<ContractListVO> clvos =null;
		if( contractHistories !=null){
			clvos = new ArrayList();
			for(ContractHistory contractHistory : contractHistories){
				ContractListVO clvo = new ContractListVO();
				clvo.setId(contractHistory.getId());
				clvo.setContractId(contractHistory.getContractId());
				clvo.setName(contractHistory.getName());
				clvo.setOperatorName(contractHistory.getOperatorName());
				ContractState cs = contractStateService.findByStateId(contractHistory.getState());
				clvo.setState(cs.getStateName());
				clvo.setSummary(contractHistory.getSummary());
				clvos.add(clvo);
			}
		}
		return new ModelAndView("pages/contractHistoryList","contractHistories",clvos).addObject("user",person);
	}
	@RequestMapping("{id}")
	@Transactional(readOnly = true)
	public ModelAndView view(@PathVariable("id") ContractHistory contractHistory, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		long contractId = contractHistory.getId();
		long contractPId = contractHistory.getParentContractId();
		String parentName = "无";
		if(contractPId > 0){
			Contract cc = contractService.findById(contractPId);
			parentName = cc.getName();
		}
		ContractGroup cg = contractGroupService.findByGroupId(contractHistory.getGroupId());
		ContractType ct = contractTypeService.findByTypeId(contractHistory.getType());
		ContractState cs = contractStateService.findByStateId(contractHistory.getState());
		return new ModelAndView("pages/contractHistoryView","contractHistory", contractHistory).addObject("groupName",cg.getGroupName()).addObject("parentName", parentName).addObject("typeName", ct.getTypeName()).addObject("state", cs.getStateName()).addObject("user",person);
	}
}
