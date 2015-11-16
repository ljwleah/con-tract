package com.neeq.contractManagementSys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>, JpaSpecificationExecutor<Contract>{
	@Query("select c from Contract c where name like ? or operatorName like ? order by id")
	public List<Contract> search(String key,String key1);
	@Query("select c from Contract c where groupId =?")
	public List<Contract> searchByGroupId(Integer groupId);
	@Query("select c from Contract c where operatorName = ?")
	public List<Contract> searchByOperatorName(String name);
}
