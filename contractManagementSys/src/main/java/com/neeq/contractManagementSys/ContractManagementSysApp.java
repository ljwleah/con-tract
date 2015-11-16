package com.neeq.contractManagementSys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ContractManagementSysApp{


	public static void main(String[] args) throws Exception {	
		System.setProperty("java.net.preferIPv4Stack", "true"); 
		SpringApplication.run(ContractManagementSysApp.class, args);
	}
}