package com.integrativeproject.IntegrativeProject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailDTO {
    private Long id;
    private String productName;
    private Integer productQuantity;
    private Double price;
    private Double subTotal;

}
