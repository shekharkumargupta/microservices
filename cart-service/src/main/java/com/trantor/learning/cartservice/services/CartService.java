package com.trantor.learning.cartservice.services;

import com.trantor.learning.cartservice.dto.CartDTO;
import com.trantor.learning.cartservice.dto.CartItemDTO;

import java.util.List;
import java.util.Optional;

public interface CartService {


    Optional<CartDTO> findById(Long id);
    Optional<List<CartDTO>> findAll();

    Optional<CartDTO> create(CartDTO cartDTO);
    Optional<CartDTO> addCartItem(CartItemDTO cartItemDTO);
}
