package com.integrativeproject.IntegrativeProject.service;

import com.integrativeproject.IntegrativeProject.dto.BranchDTO;
import com.integrativeproject.IntegrativeProject.model.Branch;

import java.util.List;

public interface IBranchService {

    List<BranchDTO> getBranches();
    BranchDTO createBranch(BranchDTO branchDto);
    BranchDTO updateBranch(Long id, BranchDTO branchDto);
    void deleteBranch(Long id);



}
