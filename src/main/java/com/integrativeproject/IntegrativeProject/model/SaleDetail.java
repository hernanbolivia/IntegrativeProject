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
    @ManyToOne
    private Sale sale;

    //Product
    @ManyToOne
    private Product product;
    private Integer productQuantity;
    private Double price;
}
