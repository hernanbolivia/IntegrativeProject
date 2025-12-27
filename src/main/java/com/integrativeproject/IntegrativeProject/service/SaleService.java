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
public class SaleService implements ISaleService {

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

        for (Sale s : sales) {
            dto = Mapper.toDTO(s);
            salesDto.add(dto);
        }
        return salesDto;

    }

    /**
     * Creates sale with details; validates inputs; persists result
     */
    @Override
    public SaleDTO createSale(SaleDTO saleDto) {

        //Validations
        if (saleDto == null) throw new RuntimeException("Sale not found");
        if (saleDto.getBranchId() == null) throw new RuntimeException("Sale id not found");
        if (saleDto.getDetails() == null || saleDto.getDetails().isEmpty())
            throw new RuntimeException("It must include at least one product");

        //Find the branch

        Branch bra = branchRepo.findById(saleDto.getBranchId()).orElse(null);
        if (bra == null) {
            throw new RuntimeException("Branch not found");
        }

        //Create the sale
        Sale sal = new Sale();
        sal.setDate(saleDto.getDate());
        sal.setStatus(saleDto.getStatus());
        sal.setBranch(bra);
        sal.setTotal(saleDto.getTotal());


        //The Details list
        //Here is the products
        List<SaleDetail> details = new ArrayList<>();

        // Validates product existence; throws if not found
        for (SaleDetailDTO salDetDTO : saleDto.getDetails()) {
            Product p = productRepo.findByName(salDetDTO.getProductName()).orElse(null);
            if (p == null) {
                throw new RuntimeException("Product not found: " + salDetDTO.getProductName());
            }

            //Create Detail

            SaleDetail saleDet = new SaleDetail();
            saleDet.setProduct(p);
            saleDet.setPrice(salDetDTO.getPrice());
            saleDet.setProductQuantity(salDetDTO.getProductQuantity());
            saleDet.setSale(sal);

            details.add(saleDet);

        }
        //Setting the list Sale.
        sal.setDetails(details);

        //Save in the BD
        saleRepo.save(sal);

        //Mapping exit
        SaleDTO exitSale = Mapper.toDTO(sal);

        return exitSale;

    }


        /**
         * Updates sale if exists; returns null
         */
        @Override
        public SaleDTO updateSale (Long id, SaleDTO saleDto){

        //Check if the sale exists to update
            Sale sal= saleRepo.findById(id).orElse(null);
            if(sal == null) throw new RuntimeException("Sale not found");

            if(saleDto.getDate()!=null) {
                sal.setDate(saleDto.getDate());

            }

            if(saleDto.getStatus()!=null) {
                sal.setStatus(saleDto.getStatus());
            }

            if(saleDto.getTotal()!=null) {
                sal.setTotal(saleDto.getTotal());
            }

            if(saleDto.getBranchId()!=null){
                Branch bra = branchRepo.findById(saleDto.getBranchId()).orElse(null);
                if(bra == null)throw new RuntimeException("Branch not found");
                sal.setBranch(bra);
            }
            saleRepo.save(sal);

            SaleDTO exitSale = Mapper.toDTO(sal);

            return exitSale;
        }

        @Override
        public void deleteSale (Long id){

            Sale sal = saleRepo.findById(id).orElse(null);
            if(sal == null) throw new RuntimeException("Sale not found");
            saleRepo.delete(sal);

        }
    }

