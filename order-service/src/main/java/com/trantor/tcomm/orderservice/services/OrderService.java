package com.trantor.tcomm.orderservice.services;

import com.trantor.tcomm.orderservice.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAll();
    OrderDTO findById(Integer id);
}
