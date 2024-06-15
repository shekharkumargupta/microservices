package com.trantor.tcomm.orderservice.repositories;

import com.trantor.tcomm.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
