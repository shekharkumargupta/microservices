package com.trantor.learning.cartservice.repositories;

import com.trantor.learning.cartservice.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
