package com.neeq.contractManagementSys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.ContractToPerson;

@Repository
public interface ContractToPersonRepository extends JpaRepository<ContractToPerson, Long>, JpaSpecificationExecutor<ContractToPerson>{
	@Query("select c from ContractToPerson c where contractId = ?")
	public List<ContractToPerson> findByContractId(Long contractId);
	
	@Query("select c from ContractToPerson c where contractId = ? and state = ?")
	public ContractToPerson findByContractIdAndState(Long contractId, int state);
}
