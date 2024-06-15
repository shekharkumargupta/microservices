package com.trantor.learning.cartservice.repositories;

import com.trantor.learning.cartservice.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
