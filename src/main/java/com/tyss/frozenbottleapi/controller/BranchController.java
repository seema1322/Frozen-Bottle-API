package com.tyss.frozenbottleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.frozenbottleapi.entity.Branch;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Branch>> savebranch(@RequestBody Branch branch)
	{
		return branchService.saveBranch(branch);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable int id)
	{
		return branchService.getBranchById(id);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody Branch branch)
	{
		return branchService.updateBranch(branch);
	}
	@GetMapping("/admin/{id}")
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranchByAdminId(@PathVariable int id)
	{
		return branchService.getBranchByAdminId(id);
	}

}
