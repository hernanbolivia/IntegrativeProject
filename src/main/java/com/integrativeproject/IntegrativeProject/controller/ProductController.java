package com.integrativeproject.IntegrativeProject.controller;



import com.integrativeproject.IntegrativeProject.dto.ProductDTO;
import com.integrativeproject.IntegrativeProject.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto){
        ProductDTO creado = productService.createProduct(dto);

        return ResponseEntity.created(URI.create("/api/products" + creado.getId())).body(creado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto){
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
