package com.trantor.learning.cartservice.controllers;

import com.trantor.learning.cartservice.dto.CartDTO;
import com.trantor.learning.cartservice.exceptions.CartNotFoundException;
import com.trantor.learning.cartservice.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;
    private KafkaTemplate<String, String> kafkaTemplate;
    private final String ORDER_TOPIC = "order-request";

    public CartController(CartService cartService, KafkaTemplate<String, String> kafkaTemplate) {
        this.cartService = cartService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/ping")
    public String ping(){
        return HttpStatus.OK.name();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartDTO>> findAll(){
        List<CartDTO> cartDTOList = cartService.findAll().get();
        return ResponseEntity.status(HttpStatus.OK).body(cartDTOList);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> findById(@PathVariable long id){

        Optional<CartDTO> optionalCartDTO = cartService.findById(id);
        if(optionalCartDTO.isPresent()){
            return ResponseEntity.ok(optionalCartDTO.get());
        }
        throw new CartNotFoundException("Could not found the cart: " + id);
    }

    @PostMapping
    public ResponseEntity<CartDTO> create(@RequestBody CartDTO cartDTO){
        Optional<CartDTO> optionalCartDTO = cartService.create(cartDTO);
        return ResponseEntity.ok(optionalCartDTO.get());
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<CartDTO> placeOrder(@RequestBody CartDTO cartDTO){
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(ORDER_TOPIC, cartDTO.toString());
        future.whenComplete((stringStringSendResult, throwable) -> {
            if(throwable == null){
                System.out.println("Message Sent: " + stringStringSendResult.getRecordMetadata().offset());
            }
        });
        return ResponseEntity.ok(cartDTO);
    }
}
