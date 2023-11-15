package com.tyss.frozenbottleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyss.frozenbottleapi.entity.Branch;


public interface BranchRepository extends JpaRepository<Branch, Integer>{

	@Query("SELECT b FROM Branch b WHERE b.user_id=?1")
	List<Branch> findByAdminId(int adminId);
}
