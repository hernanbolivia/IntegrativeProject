package com.integrativeproject.IntegrativeProject.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    // Sales Details
    private Long id;
    private LocalDate date;
    private String status;

    //Data of branch
    private Long branchId;

    //Branch List
    private List<SaleDetailDTO> details;

    //Total Sale
    private Double total;


}
