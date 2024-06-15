package com.trantor.learning.cartservice.dto;

import com.trantor.learning.cartservice.domain.Cart;
import com.trantor.learning.cartservice.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO implements Marshallable<Cart, CartDTO>{

    private int id;
    private List<CartItemDTO> cartItems;
    private String owner;
    private boolean active;

    @Override
    public CartDTO to(Cart t) {
        this.id = t.getId();
        this.owner = t.getOwner();
        this.active =t.isActive();

        List<CartItemDTO> cartItemDTOS = t.getCartItems().stream()
                .map(cartItem -> {
                    CartItemDTO cartItemDTO = new CartItemDTO();
                    cartItemDTO.to(cartItem);
                    return cartItemDTO;
                })
                .collect(Collectors.toList());
        this.setCartItems(cartItemDTOS);
        return this;
    }

    @Override
    public Cart from(CartDTO o) {
        Cart cart = new Cart();
        cart.setId(o.getId());
        cart.setOwner(o.getOwner());
        cart.setActive(o.isActive());

        List<CartItem> cartItems = o.getCartItems().stream()
                .map(cartItemDTO -> {
                    CartItem cartItem = cartItemDTO.from(cartItemDTO);
                    return cartItem;
                })
                .collect(Collectors.toList());
        cart.setCartItems(cartItems);
        return cart;
    }
}
