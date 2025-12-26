package com.integrativeproject.IntegrativeProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String status;
    private Double total;

    @ManyToOne
    private Branch branch;

    @OneToMany (mappedBy = "sale")
    private List<SaleDetail> details = new java.util.ArrayList<>();

}
