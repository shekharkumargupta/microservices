package com.trantor.tcomm.orderservice.services;

import com.trantor.tcomm.orderservice.OrderDTO;
import com.trantor.tcomm.orderservice.domain.Order;
import com.trantor.tcomm.orderservice.feignclients.ProductClient;
import com.trantor.tcomm.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductClient productClient;


    public OrderServiceImpl(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Integer id) {
        Order order = orderRepository.findById(id).get();
        return orderToOrderDTO(order);
    }

    private OrderDTO orderToOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setEmail(order.getEmail());
        orderDTO.setCart(order.getCart());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setActive(order.isActive());

        return orderDTO;
    }

}
