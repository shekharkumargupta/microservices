package com.trantor.learning.cartservice.feignclient;

import com.trantor.learning.cartservice.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("products/{id}")
    Product findById(@PathVariable("id") Integer id);
}
