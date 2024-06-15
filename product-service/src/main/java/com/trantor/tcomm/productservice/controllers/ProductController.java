package com.trantor.tcomm.productservice.controllers;

import com.trantor.tcomm.productservice.domain.Product;
import com.trantor.tcomm.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public String ping(){
        return HttpStatus.OK.name();
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id){
        return ResponseEntity.ok(productRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.ok(productRepository.save(product));
    }
}
