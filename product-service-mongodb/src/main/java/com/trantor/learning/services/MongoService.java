package com.trantor.learning.services;

import com.trantor.learning.entities.Product;

import java.util.List;

public interface MongoService {

    List<Product> findAll();
    Product save(Product product);
    List<Product> saveAll(List<Product> products);
}
