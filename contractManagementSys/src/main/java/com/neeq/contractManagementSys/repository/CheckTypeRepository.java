package com.neeq.contractManagementSys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.CheckType;
@Repository
public interface CheckTypeRepository extends JpaRepository<CheckType, Integer>,
		JpaSpecificationExecutor<CheckType> {
	@Query("select c from CheckType c where typeId = ?")
	public CheckType findByTypId(Integer typeId);
}
