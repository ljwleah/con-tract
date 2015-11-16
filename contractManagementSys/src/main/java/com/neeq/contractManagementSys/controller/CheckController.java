package com.neeq.contractManagementSys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;
import javax.validation.Valid;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.neeq.contractManagementSys.VO.CheckListVO;
import com.neeq.contractManagementSys.domain.Check;
import com.neeq.contractManagementSys.domain.CheckType;
import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.service.CheckService;
import com.neeq.contractManagementSys.service.CheckTypeService;
import com.neeq.contractManagementSys.service.PersonService;
import com.neeq.contractManagementSys.service.RenewalService;

@Controller
@RequestMapping("/check/")
public class CheckController {
	@Autowired
	private CheckService checkService;
	@Autowired
	private CheckTypeService checkTypeService;
	@Autowired
	private PersonService personService;
	@RequestMapping("list")
	@Transactional(readOnly = true)
	public ModelAndView listByContractId(Long contractId, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		List<Check> checks = this.checkService.findByContractId(contractId);
		List<CheckListVO> chlvos =new ArrayList();
		if(checks != null){
			for(Check check : checks){
				CheckListVO cl = new CheckListVO();
				cl.setActualDateOfCheck(check.getActualDateOfCheck());
				cl.setAlertDate(check.getAlertDate());
				cl.setId(check.getId());
				cl.setContent(check.getContent());
				cl.setContractId(check.getContractId());
				cl.setPlannedDateOfCheck(check.getPlannedDateOfCheck());
				CheckType ct = checkTypeService.findByTypeId(check.getType());
				cl.setTypeName(ct.getTypeName());
				chlvos.add(cl);
			}
		}
		
		return new ModelAndView("pages/checkList","checks",chlvos).addObject("contractId", contractId).addObject("user",person);
	}
	
	@RequestMapping(params = "contractId", method = RequestMethod.GET)
	public ModelAndView checkCreate(@ModelAttribute Check check, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/checkForm","user",person);
	}
	
	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Check check, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		CheckType ct = checkTypeService.findByTypeId(check.getType());
		return new ModelAndView("pages/checkView","check",check).addObject("typeName", ct.getTypeName()).addObject("user",person);
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Check check, RedirectAttributes redirect){
		Long contractId = check.getContractId();
		this.checkService.delete(check);
		redirect.addFlashAttribute("globalMessage", "Successfully delete a check");
		return new ModelAndView("redirect:/check/list", "contractId", contractId);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid Check check, BindingResult bindingResult, RedirectAttributes redirect,HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/checkForm", "formErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		check = this.checkService.save(check);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new check");
		/*return new ModelAndView("redirect:/check/{check.id}", "check.id", check.getId());*/
		return new ModelAndView("redirect:/check/list", "contractId", check.getContractId());
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Check check,HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/checkEdit", "check", check).addObject("user",person);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Check check, BindingResult bindingResult, RedirectAttributes redirect,HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/checkEdit", "editErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		check = this.checkService.save(check);
		redirect.addFlashAttribute("globalMessage", "Successfully edited a check");
		return new ModelAndView("redirect:/check/{check.id}", "check.id", check.getId());
	}
}
