package com.neeq.contractManagementSys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.domain.TimeOfPayment;
import com.neeq.contractManagementSys.service.PersonService;
import com.neeq.contractManagementSys.service.TimeOfPaymentService;

@Controller
@RequestMapping("/timeOfPayment")
public class TimeOfPaymentController {
	@Autowired
	private TimeOfPaymentService timeOfPaymentService;
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/list/{contractId}")
	@Transactional(readOnly = true)
	public ModelAndView listByContractId(@PathVariable("contractId")Long contractId, HttpServletRequest request){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		List<TimeOfPayment> timeOfPayments = this.timeOfPaymentService.findByContractId(contractId);
		return new ModelAndView("pages/timeOfPaymentList", "timeOfPayments", timeOfPayments)
					.addObject("contractId", contractId).addObject("user",person);
	}
	
	@RequestMapping(params = "contractId", method = RequestMethod.GET)
	public ModelAndView timeOfPaymentCreate(@ModelAttribute TimeOfPayment timeOfPayment,@RequestParam(value ="contractId") Long contractId, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		timeOfPayment.setContractId(contractId);
		return  new ModelAndView("pages/timeOfPaymentForm", "user", person);
	}
	
	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") TimeOfPayment timeOfPayment, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		System.out.println("Username:"+ securityContextImpl.getAuthentication().getName());
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/timeOfPaymentView","timeOfPayment",timeOfPayment).addObject("user",person);
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") TimeOfPayment timeOfPayment, RedirectAttributes redirect){
		Long contractId = timeOfPayment.getContractId();
		this.timeOfPaymentService.delete(timeOfPayment);
		redirect.addFlashAttribute("globalMessage", "Successfully delete a timeOfPayment");
		//return new ModelAndView("redirect:/timeOfPayment/list", "contractId", contractId);
		return new ModelAndView("redirect:/timeOfPayment/list/{contractId}","contractId", contractId);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid TimeOfPayment timeOfPayment, BindingResult bindingResult, RedirectAttributes redirect, HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/timeOfPaymentForm", "formErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		timeOfPayment = this.timeOfPaymentService.save(timeOfPayment);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new timeOfPayment");
		//return new ModelAndView("redirect:/timeOfPayment/{timeOfPayment.id}", "timeOfPayment.id", timeOfPayment.getId());
		return new ModelAndView("redirect:/timeOfPayment/list/{contractId}","contractId", timeOfPayment.getContractId());
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") TimeOfPayment timeOfPayment, HttpServletRequest request) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/timeOfPaymentEdit", "timeOfPayment", timeOfPayment).addObject("user",person);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid TimeOfPayment timeOfPayment, BindingResult bindingResult, RedirectAttributes redirect, HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/timeOfPaymentEdit", "editErrors", bindingResult.getAllErrors()).addObject("user",person);
		}
		timeOfPayment = this.timeOfPaymentService.save(timeOfPayment);
		redirect.addFlashAttribute("globalMessage", "Successfully edited a timeOfPayment");
		return new ModelAndView("redirect:/timeOfPayment/{timeOfPayment.id}", "timeOfPayment.id", timeOfPayment.getId());
	}
}
