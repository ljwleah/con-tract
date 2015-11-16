package com.neeq.contractManagementSys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.Renewal;
import com.neeq.contractManagementSys.domain.TimeOfPayment;

@Repository
public interface TimeOfPaymentRepository extends JpaRepository<TimeOfPayment, Long>, JpaSpecificationExecutor<TimeOfPayment>{
	@Query("select t from TimeOfPayment t where contractId = ?")
	public List<TimeOfPayment> findByContractId(Long contractId);
	
	@Query("select t from TimeOfPayment t where alertDate = ?")
	public List<TimeOfPayment> findByAlertDate(Date alertDate);
}
