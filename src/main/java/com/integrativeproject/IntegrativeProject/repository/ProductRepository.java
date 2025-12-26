package com.integrativeproject.IntegrativeProject.repository;

import com.integrativeproject.IntegrativeProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long>{

    //Searching for poroduct by name
    Optional<Product> findByName(String name);
}
