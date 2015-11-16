package com.neeq.contractManagementSys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.ContractState;
@Repository
public interface ContractStateRepository extends JpaRepository<ContractState, Long>,
		JpaSpecificationExecutor<ContractState> {
	@Query("select c from ContractState c where stateId = ?")
	public ContractState findByStateId(Long stateId);
	
}
