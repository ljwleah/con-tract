package com.neeq.contractManagementSys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import com.neeq.contractManagementSys.service.PersonService;

@Controller
@RequestMapping("/person/")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@RequestMapping
	@Transactional(readOnly=true)
	public ModelAndView listAll(HttpServletRequest request) {		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		Person person = personService.findByName(securityContextImpl.getAuthentication().getName());
		List<Person> persons = this.personService.findAll();
		return new ModelAndView("pages/personList", "persons", persons).addObject("user", person);
	}
	
	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Person person, HttpServletRequest request) {		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		Person person1 = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/personView","person", person).addObject("user", person1);
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Person person, HttpServletRequest request) {		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		Person person1 = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/personEdit", "person", person).addObject("user", person1);
	}
	
	@RequestMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Person person, RedirectAttributes redirect) {
		//fix me 
		//需要加入中间表的删除
		this.personService.delete(person);
		redirect.addFlashAttribute("globalMessage", "Successfully delete a person");
		return new ModelAndView("redirect:/person/");
	}
	
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public ModelAndView createForm(@ModelAttribute Person person, HttpServletRequest request) {		
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
				// 登录名
		Person person1 = personService.findByName(securityContextImpl.getAuthentication().getName());
		return new ModelAndView("pages/personForm").addObject("user", person1);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@Valid Person person, BindingResult bindingResult, RedirectAttributes redirect, HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
					 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
					// 登录名
			Person person1 = personService.findByName(securityContextImpl.getAuthentication().getName());
			return new ModelAndView("pages/personForm", "formErrors", bindingResult.getAllErrors()).addObject("user", person1);
		}
		person.setGroupId(100);
		person.setPassword(person.getName());
		person.setRole("ADMIN");
		person = this.personService.save(person);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new person");
		return new ModelAndView("redirect:/person/{person.id}", "person.id", person.getId());
		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@Valid Person person, BindingResult bindingResult, RedirectAttributes redirect){
		if (bindingResult.hasErrors()) {
			return new ModelAndView("pages/personEdit", "editErrors", bindingResult.getAllErrors());
		}
		person = this.personService.save(person);
		redirect.addFlashAttribute("globalMessage", "Successfully edited a person");
		return new ModelAndView("redirect:/person/{person.id}", "person.id", person.getId());
	}
}
