package com.trantor.learning.cartservice.services;

import com.trantor.learning.cartservice.domain.Cart;
import com.trantor.learning.cartservice.domain.Product;
import com.trantor.learning.cartservice.dto.CartDTO;
import com.trantor.learning.cartservice.dto.CartItemDTO;
import com.trantor.learning.cartservice.exceptions.CartNotFoundException;
import com.trantor.learning.cartservice.feignclient.ProductClient;
import com.trantor.learning.cartservice.repositories.CartItemRepository;
import com.trantor.learning.cartservice.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductClient productClient;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           ProductClient productClient) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productClient = productClient;
    }

    @Override
    public Optional<CartDTO> create(CartDTO cartDTO) {
        Cart cart = cartRepository.save(cartDTO.from(cartDTO));
        return Optional.of(new CartDTO().to(cart));
    }

    @Override
    public Optional<CartDTO> findById(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        CartDTO cartDTO = new CartDTO().to(cartOptional.get());
        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItems().stream()
                .map(cartItemDTO -> {
                    Product product = productClient.findById(cartItemDTO.getProduct());
                    cartItemDTO.setProductName(product.getName());
                    return cartItemDTO;
                })
                .collect(Collectors.toList());
        cartDTO.setCartItems(cartItemDTOList);
        return Optional.of(cartDTO);
    }

    @Override
    public Optional<List<CartDTO>> findAll() {
        List<CartDTO> cartDTOS = cartRepository.findAll()
                .stream()
                .map(cart -> {
                    CartDTO cartDTO = new CartDTO().to(cart);
                    List<CartItemDTO> cartItemDTOS = cart.getCartItems()
                            .stream()
                            .map(cartItem -> {
                                CartItemDTO cartItemDTO = new CartItemDTO().to(cartItem);
                                Product product = productClient.findById(cartItem.getProduct());
                                cartItemDTO.setProductName(product.getName());
                                return cartItemDTO;
                            }).collect(Collectors.toList());
                    cartDTO.setCartItems(cartItemDTOS);
                    return cartDTO;
                })
                .collect(Collectors.toList());
        return Optional.of(cartDTOS);
    }

    @Override
    public Optional<CartDTO> addCartItem(CartItemDTO cartItemDTO) {
        Optional<Cart> cart = cartRepository.findById((long) cartItemDTO.getCart());
        if(!cart.isPresent()){
            throw new CartNotFoundException("Invalid Cart");
        }
        cart.get().getCartItems().add(new CartItemDTO().from(cartItemDTO));
        return Optional.of(new CartDTO().to(cart.get()));
    }

}
