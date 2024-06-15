package com.trantor.learning.cartservice.dto;

import com.trantor.learning.cartservice.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO implements Marshallable<CartItem, CartItemDTO> {

    private int id;
    private int cart;
    private int product;
    private String productName;
    private double price;
    private int qty;
    private double total;

    @Override
    public CartItemDTO to(CartItem t) {
        this.id = t.getId();
        this.cart = t.getCart();
        this.product = t.getProduct();
        this.price = t.getPrice();
        this.qty = t.getQty();
        this.total = t.getTotal();
        return this;
    }

    @Override
    public CartItem from(CartItemDTO o) {
        CartItem cartItem = new CartItem();
        cartItem.setId(o.getId());
        cartItem.setCart(o.getCart());
        cartItem.setProduct(o.getProduct());
        cartItem.setPrice(o.getPrice());
        cartItem.setQty(o.getQty());
        cartItem.setTotal(o.getTotal());
        return cartItem;
    }
}
