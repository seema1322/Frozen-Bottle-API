package com.tyss.frozenbottleapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.frozenbottleapi.entity.Branch;
import com.tyss.frozenbottleapi.repository.BranchRepository;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch saveBranch(Branch branch)
	{
		return branchRepository.save(branch);
	}
	public Branch getBranchById(int id)
	{
		Optional<Branch> opt=branchRepository.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			return null;
		}
	}
	public Branch updateBranch(Branch branch)
	{
		return branchRepository.save(branch);
	}
	public List<Branch> getBranchByAdminId(int adminId)
	{
		return branchRepository.findByAdminId(adminId);
	}

}
