package com.trantor.learning.services;

import com.trantor.learning.entities.Product;
import com.trantor.learning.repositories.ProductRepository;

import java.util.List;

public class MongoServiceImpl implements MongoService{


    private ProductRepository productRepository;

    public MongoServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return null;
    }
}
