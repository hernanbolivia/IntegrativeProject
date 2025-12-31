package com.integrativeproject.IntegrativeProject.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Sale
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "saleId")
    private Sale sale;

    //Product
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;
    private Integer productQuantity;
    private Double price;
}
