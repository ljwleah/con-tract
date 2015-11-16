package com.neeq.contractManagementSys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;




import com.neeq.contractManagementSys.domain.ContractGroup;

@Repository
public  interface ContractGroupRepository  extends JpaRepository<ContractGroup, Long>, JpaSpecificationExecutor<ContractGroup>{
	@Query("select c from ContractGroup c where groupId = ?")
	public ContractGroup findByGroupId(Integer integer);
}
