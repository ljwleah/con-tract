package com.neeq.contractManagementSys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.ContractType;
@Repository
public interface ContractTypeRepository extends
		JpaRepository<ContractType, Long>,
		JpaSpecificationExecutor<ContractType> {
	@Query("select c from ContractType c where typeId = ?")
	public ContractType findByTypeId(Long typeId);

}
