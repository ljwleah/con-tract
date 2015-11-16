package com.neeq.contractManagementSys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.Check;

@Repository
public interface CheckRepository  extends JpaRepository<Check, Long>, JpaSpecificationExecutor<Check>{
	@Query("select c from Check c where contractId = ?")
	public List<Check> findByContractId(Long contractId);
	
	@Query("select c from Check c where alertDate = ?")
	public List<Check> findByAlertDate(Date alertDate);
}
