package com.integrativeproject.IntegrativeProject.controller;

import com.integrativeproject.IntegrativeProject.dto.SaleDTO;
import com.integrativeproject.IntegrativeProject.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {


    @Autowired
    private ISaleService saleService;


    @GetMapping
    public ResponseEntity<List<SaleDTO>> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }



    /*
     * Creates a single sale using the SaleDTO provided in the request.
     * This approach expects the DTO to contain all necessary information,
     * without requiring separate requests.
     */

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(SaleDTO dto){
        SaleDTO created = saleService.createSale(dto);
        return ResponseEntity.created(URI.create("/api/sales"+ created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public SaleDTO update(@PathVariable Long id, @RequestBody SaleDTO dto){
        return saleService.updateSale(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();

    }

}





