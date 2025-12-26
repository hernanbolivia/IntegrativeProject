package com.integrativeproject.IntegrativeProject.mapper;

import com.integrativeproject.IntegrativeProject.dto.BranchDTO;
import com.integrativeproject.IntegrativeProject.dto.ProductDTO;
import com.integrativeproject.IntegrativeProject.dto.SaleDTO;
import com.integrativeproject.IntegrativeProject.dto.SaleDetailDTO;
import com.integrativeproject.IntegrativeProject.model.Branch;
import com.integrativeproject.IntegrativeProject.model.Product;
import com.integrativeproject.IntegrativeProject.model.Sale;
import com.integrativeproject.IntegrativeProject.model.SaleDetail;

import java.util.stream.Collectors;

public class Mapper {

    //Mapping Product to ProductDTO
    public static ProductDTO toDTO(Product p){
        if(p == null)return null;

        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .price(p.getPrice())
                .build();
    }

    //Mapping Branch to BranchDTO
    public static BranchDTO toDTO(Branch b){
        if(b == null)return null;

        // Maps branch details to data transfer object
        return BranchDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getAddress())
                .build();
    }

    //Mapping Sale to SaleDTO
    public static SaleDTO toDTO(Sale sale){
        if(sale == null)return null;

        // Maps product details to data transfer object
        var detail = sale.getDetails().stream().map(det -> SaleDetailDTO.builder()

                        .id(det.getProduct().getId())
                        .productName(det.getProduct().getName())
                        .productQuantity(det.getProductQuantity())
                        .price(det.getPrice())
                        .subTotal(det.getPrice()* det.getProductQuantity())
                        .build())
                .toList();

        var total = detail.stream()
                .map(SaleDetailDTO::getSubTotal)
                .reduce(0.0, Double::sum);


        // Maps sale attributes to data transfer object
        return SaleDTO.builder()
                .id(sale.getId())
                .date(sale.getDate())
                .branchId(sale.getBranch().getId())
                .status(sale.getStatus())
                .details(detail)
                .total(total)
                .build();

    }


}
