package com.neeq.contractManagementSys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository pr;
	
	@Transactional
	public Person save(Person person){
		return pr.save(person);
	}
	
	@Transactional
	public List<Person> findAll(){
		return pr.findAll();
	}
	@Transactional
	public Person findByName(String name){
		return pr.findByName(name);
	}
	@Transactional
	public void delete(Person person){
		this.pr.delete(person);
	}
}
