package com.trantor.tcomm.orderservice.controllers;

import com.trantor.tcomm.orderservice.OrderDTO;
import com.trantor.tcomm.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public String ping(){
        return HttpStatus.OK.name();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable int id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findById(id));
    }

}
