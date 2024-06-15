package com.trantor.tcomm.productservice.repositories;

import com.trantor.tcomm.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
