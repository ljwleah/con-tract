package com.neeq.contractManagementSys.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neeq.contractManagementSys.domain.Manager;
import com.neeq.contractManagementSys.domain.Renewal;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long>, JpaSpecificationExecutor<Manager>{
	@Query("select m from Manager m where groupId = ?")
	public List<Manager> findByGroupId(Long groupId);
}
