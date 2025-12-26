package com.integrativeproject.IntegrativeProject.service;

import com.integrativeproject.IntegrativeProject.dto.SaleDTO;
import com.integrativeproject.IntegrativeProject.dto.SaleDetailDTO;
import com.integrativeproject.IntegrativeProject.mapper.Mapper;
import com.integrativeproject.IntegrativeProject.model.Branch;
import com.integrativeproject.IntegrativeProject.model.Product;
import com.integrativeproject.IntegrativeProject.model.Sale;
import com.integrativeproject.IntegrativeProject.model.SaleDetail;
import com.integrativeproject.IntegrativeProject.repository.BranchRepository;
import com.integrativeproject.IntegrativeProject.repository.ProductRepository;
import com.integrativeproject.IntegrativeProject.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private SaleRepository saleRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private BranchRepository branchRepo;





    @Override
    public List<SaleDTO> getSales() {

        List<Sale> sales = saleRepo.findAll();
        List<SaleDTO> salesDto = new ArrayList<>();
        SaleDTO dto;

        for(Sale s : sales){
            dto = Mapper.toDTO(s);
            salesDto.add(dto);
        }
        return salesDto;

    }

    @Override
    public SaleDTO createSale(SaleDTO saleDto) {

        //Validations
        if (saleDto == null) throw new RuntimeException("Sale not found");
        if (saleDto.getId() == null) throw new RuntimeException("Sale id not found");
        if (saleDto.getDetails() == null || saleDto.getDetails().isEmpty())
            throw new RuntimeException("It must include at least one product");

        //Find the branch

        Branch bra = branchRepo.findById(saleDto.getBranchId()).orElse(null);
        if(bra == null)
            throw new RuntimeException("Branch not found");

        //Create the sale
        Sale sal = new Sale();
        sal.setDate(saleDto.getDate());
        sal.setStatus(saleDto.getStatus());
        sal.setBranch(bra);
        sal.setTotal(saleDto.getTotal());



        //The Details list
        //Here is the products
        List<SaleDetail> details = new ArrayList<>();

        for(SaleDetailDTO salDetDTO : saleDto.getDetails()){
            Product p = productRepo.findByName(salDetDTO.getProductName()).orElse(null);
            if(p == null)
                throw new RuntimeException("Product not found");
        }




        return null;
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDto) {
        return null;
    }

    @Override
    public void deleteSale(Long id) {

    }
}
