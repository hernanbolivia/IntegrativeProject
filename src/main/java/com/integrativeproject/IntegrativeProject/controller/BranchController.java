package com.integrativeproject.IntegrativeProject.controller;

import com.integrativeproject.IntegrativeProject.dto.BranchDTO;
import com.integrativeproject.IntegrativeProject.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private IBranchService branchService;


    @GetMapping
    public ResponseEntity<List<BranchDTO>> getBranches(){
        return ResponseEntity.ok(branchService.getBranches());
    }


    @PostMapping
    public ResponseEntity<BranchDTO> create(@RequestBody BranchDTO dto){
        BranchDTO created = branchService.createBranch(dto);
        return ResponseEntity.created(URI.create("/api/branches" + created.getId())).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> update(@PathVariable Long id, @RequestBody BranchDTO dto){
        return ResponseEntity.ok(branchService.updateBranch(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
