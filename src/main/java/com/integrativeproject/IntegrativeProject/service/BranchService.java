package com.integrativeproject.IntegrativeProject.service;

import com.integrativeproject.IntegrativeProject.dto.BranchDTO;
import com.integrativeproject.IntegrativeProject.mapper.Mapper;
import com.integrativeproject.IntegrativeProject.model.Branch;
import com.integrativeproject.IntegrativeProject.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    @Autowired
    private BranchRepository repo;


    @Override
    public List<BranchDTO> getBranches() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDto) {
        Branch bra = Branch.builder()
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .build();

        return Mapper.toDTO(repo.save(bra));
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDto) {
        Branch bra = repo.findById(id).orElseThrow(() -> new RuntimeException("Branch not found"));

        bra.setName(branchDto.getName());
        bra.setAddress(branchDto.getAddress());

        return Mapper.toDTO(repo.save(bra));
    }

    @Override
    public void deleteBranch(Long id) {
        if(!repo.existsById(id))
            throw new RuntimeException("Branch not found");

        repo.deleteById(id);

    }
}
