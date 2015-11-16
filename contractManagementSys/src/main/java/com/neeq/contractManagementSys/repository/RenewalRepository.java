package com.neeq.contractManagementSys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.Renewal;

@Repository
public interface RenewalRepository extends JpaRepository<Renewal, Long>, JpaSpecificationExecutor<Renewal>{
	@Query("select r from Renewal r where contractId = ?")
	public List<Renewal> findByContractId(Long contractId);
	
	@Query("select r from Renewal r where alertDate = ?")
	public List<Renewal> findByAlertDate(Date alertDate);
}
