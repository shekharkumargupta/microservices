package com.trantor.tcomm.orderservice.feignclients;

import com.trantor.tcomm.orderservice.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("products/{id}")
    Product findById(@PathVariable("id")Integer id);
}
