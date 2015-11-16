package com.neeq.contractManagementSys.controller;

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

import com.neeq.contractManagementSys.domain.Contract;
import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.service.PersonService;
import com.neeq.contractManagementSys.service.RenewalService;

@Controller
@RequestMapping("/renewal/")
public class RenewalController {
	@Autowired
	private RenewalService renewalService;
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
		List<Renewal> renewals = this.renewalService.findByContractId(contractId);
		return new ModelAndView("pages/renewalList","renewals",renewals).addObject("contractId", contractId).addObject("user",person);
	}
	
	@RequestMapping(params = "contractId", method = RequestMethod.GET)
	public ModelAndView renewalCreate(@ModelAttribute Renewal renewal, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/renewalForm","user",person);
	}
	
	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Renewal renewal, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/renewalView","renewal",renewal).addObject("user",person);
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Renewal renewal, RedirectAttributes redirect){
		long contractId = renewal.getContractId();
		this.renewalService.delete(renewal);
		redirect.addFlashAttribute("globalMessage", "Successfully delete a renewal");
		return new ModelAndView("redirect:/renewal/list","contractId", contractId);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid Renewal renewal, BindingResult bindingResult, RedirectAttributes redirect){
		if (bindingResult.hasErrors()) {
			return new ModelAndView("pages/renewalForm", "formErrors", bindingResult.getAllErrors());
		}
		renewal = this.renewalService.save(renewal);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new renewal");
		/*return new ModelAndView("redirect:/renewal/{renewal.id}", "renewal.id", renewal.getId());*/
		return new ModelAndView("redirect:/renewal/list","contractId", renewal.getContractId());
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Renewal renewal, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/renewalEdit", "renewal", renewal).addObject("user",person);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Renewal renewal, BindingResult bindingResult, RedirectAttributes redirect, HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/renewalEdit", "editErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		renewal = this.renewalService.save(renewal);
		redirect.addFlashAttribute("globalMessage", "Successfully edited a renewal");
		return new ModelAndView("redirect:/renewal/{renewal.id}", "renewal.id", renewal.getId());
	}
}
