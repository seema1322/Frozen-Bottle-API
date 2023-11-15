package com.tyss.frozenbottleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.BranchDao;
import com.tyss.frozenbottleapi.entity.Branch;

import com.tyss.frozenbottleapi.exceptions.BranchNotFoundException;

import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@Service
public class BranchService {

	@Autowired
	private BranchDao branchDao;
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch) {
		Branch savedBranch = branchDao.saveBranch(branch);
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("branch saved");
		responseStructure.setData(savedBranch);
		return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.CREATED);

	}
	
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int id)
	{
		Branch branch=branchDao.getBranchById(id);
		if(branch !=null)
		{
			ResponseStructure<Branch> responseStructure=new ResponseStructure<Branch>();
			responseStructure.setData(branch);
			responseStructure.setMessage("branch with id : "+id+" is found");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.FOUND);
		}
		else
		{
			throw new BranchNotFoundException("No Branch Exist With id :"+id);
		}
	}
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch branch) {

		int id = branch.getId();
		Branch branch2 = branchDao.getBranchById(id);
		if (branch2 != null) {
			Branch updatedBranch = branchDao.updateBranch(branch);
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setData(updatedBranch);
			responseStructure.setMessage("branch updated");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			throw new BranchNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchByAdminId(int id) {
		List<Branch> branches = branchDao.getBranchByAdminId(id);
		if (branches != null) {
			ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
			responseStructure.setData(branches);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("All Branches with admin id : " + id + " are found");
			return new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new BranchNotFoundException("No branch is under admin id " + id);
		}
	}
}
