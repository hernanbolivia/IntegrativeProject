package com.integrativeproject.IntegrativeProject.service;

import com.integrativeproject.IntegrativeProject.dto.ProductDTO;
import com.integrativeproject.IntegrativeProject.exception.NotFoundException;
import com.integrativeproject.IntegrativeProject.mapper.Mapper;
import com.integrativeproject.IntegrativeProject.model.Product;
import com.integrativeproject.IntegrativeProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> getProducts() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {

        // Builds product from DTO properties
        Product prod = Product.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
        return Mapper.toDTO(repo.save(prod));

    }

    /**
     * Updates product if exists; persists changes
     */
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDto) {

        //Check if this product exists
        Product prod = repo.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        prod.setName(productDto.getName());
        prod.setCategory(productDto.getCategory());
        prod.setPrice(productDto.getPrice());
        prod.setQuantity(productDto.getQuantity());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        repo.deleteById(id);

    }
}
