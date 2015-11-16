package com.neeq.contractManagementSys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;
import javax.validation.Valid;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;import org.springframework.http.HttpRequest;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neeq.contractManagementSys.VO.ContractListVO;
import com.neeq.contractManagementSys.VO.ContractVO;
import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.domain.ContractGroup;
import com.neeq.contractManagementSys.domain.ContractHistory;
import com.neeq.contractManagementSys.domain.ContractState;
import com.neeq.contractManagementSys.domain.ContractType;
import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.repository.ContractRepository;
import com.neeq.contractManagementSys.service.ContractGroupService;
import com.neeq.contractManagementSys.service.ContractHistoryService;
import com.neeq.contractManagementSys.service.ContractService;
import com.neeq.contractManagementSys.service.ContractStateService;
import com.neeq.contractManagementSys.service.ContractTypeService;
import com.neeq.contractManagementSys.service.PersonService;

@Controller
@RequestMapping("/")
public class ContractController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private ContractHistoryService contractHistoryService;
	@Autowired
	private ContractGroupService contractGroupService;
	@Autowired
	private ContractTypeService contractTypeService;
	@Autowired
	
	private ContractStateService contractStateService;
	@Autowired
	private PersonService personService;
	
	@RequestMapping
	@Transactional(readOnly = true)
	public ModelAndView listAll(HttpServletRequest request) {		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
	
		List<Contract> contracts = null;
		//当前用户groupId是0，可以看到所有合同
		if(person.getGroupId() == 0)
			contracts = this.contractService.findAll();
		//当前用户groupID不是100，即为小组负责人，可以查看该组的所有合同
		else if(person.getGroupId() != 100)
			contracts = this.contractService.findByGroupID(person.getGroupId());
		//groupId是100，即为一般经办人，可以查看自己过手的合同
		else contracts = this.contractService.findByName(person.getName());
		List<ContractListVO> clvos =null;
		if( contracts !=null){
			clvos = new ArrayList();
			for(Contract contract : contracts){
				ContractListVO clvo = new ContractListVO();
				clvo.setId(contract.getId());
				clvo.setContractId(contract.getContractId());
				clvo.setName(contract.getName());
				clvo.setOperatorName(contract.getOperatorName());
				ContractState cs = contractStateService.findByStateId(contract.getState());
				clvo.setState(cs.getStateName());
				clvos.add(clvo);
			}
		}
		return new ModelAndView("pages/list", "contracts", clvos).addObject("user",person);
	}
	@RequestMapping("/select/{key}")
	@Transactional(readOnly = true)
	public ModelAndView selectList(@PathVariable String key) {		
		List<Contract> contracts = this.contractService.findBykey("%"+key+"%");
		List<ContractListVO> clvos =new ArrayList();
		if( contracts !=null){
			for(Contract contract : contracts){
				ContractListVO clvo = new ContractListVO();
				clvo.setId(contract.getId());
				clvo.setContractId(contract.getContractId());
				clvo.setName(contract.getName());
				clvo.setOperatorName(contract.getOperatorName());
				ContractState cs = contractStateService.findByStateId(contract.getState());
				clvo.setState(cs.getStateName());
				clvos.add(clvo);
			}
		}
		return new ModelAndView("pages/list", "contracts", clvos);
	}
	
	@RequestMapping("login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Contract contract, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		request.getSession().setAttribute("person", person);
		long contractId = contract.getId();
		long contractPId = contract.getParentContractId();
		String parentName = "无";
		if(contractPId > 0){
			Contract cc = contractService.findById(contractPId);
			parentName = cc.getName();
		}
		ContractGroup cg = contractGroupService.findByGroupId(contract.getGroupId());
		ContractType ct = contractTypeService.findByTypeId(contract.getType());
		ContractState cs = contractStateService.findByStateId(contract.getState());
		return new ModelAndView("pages/view","contract", contract).addObject("groupName",cg.getGroupName()).addObject("parentName", parentName).addObject("typeName", ct.getTypeName()).addObject("state", cs.getStateName()).addObject("user",person);
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Contract contract, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		request.getSession().setAttribute("person", person);
		List<Contract> contracts = this.contractService.findAll();
		return new ModelAndView("pages/edit", "contract", contract).addObject("contracts", contracts).addObject("user",person);
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Contract contract, RedirectAttributes redirect) {
		//fix me 
		//需要加入中间表的删除
		this.contractService.delete(contract);
		redirect.addFlashAttribute("globalMessage", "Successfully delete a contract");
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public ModelAndView createForm(@ModelAttribute ContractVO contractVO, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		request.getSession().setAttribute("person", person);
		List<Contract> contracts = this.contractService.findAll();
		return new ModelAndView("pages/form","contracts",contracts).addObject("user",person);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid ContractVO contractVO, BindingResult bindingResult, RedirectAttributes redirect, HttpServletRequest request) {		
		
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/form", "formErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		Contract contract = contractVO.transToContract();
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		contract.setOperatorName(person.getName());
		contract.setEmail(person.getEmail());
		contract = this.contractService.save(contract);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new contract");
		return new ModelAndView("redirect:/{contract.id}", "contract.id", contract.getId());
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Contract contract, BindingResult bindingResult, RedirectAttributes redirect, HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/edit", "editErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		Contract contractTemp = this.contractService.findById(contract.getId());
		ContractHistory contractHistory = new ContractHistory();
		contractHistory.setOriginalId(contractTemp.getId());
		contractHistory.setContractId(contractTemp.getContractId());
		contractHistory.setContractDocumentPath(contractTemp.getContractDocumentPath());
		contractHistory.setContent(contractTemp.getContent());
		contractHistory.setEmail(contractTemp.getEmail());
		contractHistory.setEndDate(contractTemp.getEndDate());
		contractHistory.setGroupId(contractTemp.getGroupId());
		contractHistory.setName(contractTemp.getName());
		contractHistory.setOperatorEndDate(contractTemp.getOperatorEndDate());
		contractHistory.setOperatorName(contractTemp.getOperatorName());
		contractHistory.setOperatorStartDate(contractTemp.getOperatorStartDate());
		contractHistory.setParentContractId(contractTemp.getParentContractId());
		contractHistory.setPartyB(contractTemp.getPartyB());
		contractHistory.setRemark(contractTemp.getRemark());
		contractHistory.setSignDate(contractTemp.getSignDate());
		contractHistory.setStartingDate(contractTemp.getStartingDate());
		contractHistory.setState(contractTemp.getState());
		contractHistory.setSummary(contractTemp.getSummary());
		contractHistory.setTotal(contractTemp.getTotal());
		contractHistory.setType(contractTemp.getType());
		contractHistory.setValidPeriod(contractTemp.getValidPeriod());
		this.contractHistoryService.save(contractHistory);
		contract = this.contractService.save(contract);
		
		redirect.addFlashAttribute("globalMessage", "Successfully edited a contract");
		return new ModelAndView("redirect:/{contract.id}", "contract.id", contract.getId());
	}
	
	
	
}
