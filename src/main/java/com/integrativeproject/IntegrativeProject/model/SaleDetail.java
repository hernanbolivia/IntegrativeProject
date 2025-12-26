package com.integrativeproject.IntegrativeProject.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Sale
    @ManyToOne
    private Sale sale;

    //Product
    @ManyToOne
    private Product product;
    private Integer productQuantity;
    private Double price;
}
