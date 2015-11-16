package com.neeq.contractManagementSys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.ContractHistory;
import com.neeq.contractManagementSys.domain.Renewal;

@Repository
public interface ContractHistoryRepository extends JpaRepository<ContractHistory, Long>, JpaSpecificationExecutor<ContractHistory>{
	@Query("select c from ContractHistory c where originalId = ?")
	public List<ContractHistory> findByOriginalId(Long originalId);
}
