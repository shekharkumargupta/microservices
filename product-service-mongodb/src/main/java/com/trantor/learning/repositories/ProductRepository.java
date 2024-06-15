package com.trantor.learning.repositories;

import com.trantor.learning.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    public Product findProductByTitle(String title);
    public List<Product> findAllByBrand(String brand);

    @Query(value = "{price: {$gt: 100}, title: 'iPhone 9'}", fields = "{}")
    public List<Product> findAll();
}
