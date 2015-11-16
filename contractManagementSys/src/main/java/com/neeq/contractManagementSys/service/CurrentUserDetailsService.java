package com.neeq.contractManagementSys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neeq.contractManagementSys.domain.CurrentUser;
import com.neeq.contractManagementSys.domain.Person;
@Service
public class CurrentUserDetailsService implements UserDetailsService {

	    @Autowired
	    private PersonService personService;

	    @Override
	    public CurrentUser loadUserByUsername(String name) throws UsernameNotFoundException {
	        //Person user = personService.findByName(name).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", name)));
	    	Person user = null;
			try {
				user = personService.findByName(name);
			} catch (UsernameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    	
	    	return new CurrentUser(user);
	    }
}
