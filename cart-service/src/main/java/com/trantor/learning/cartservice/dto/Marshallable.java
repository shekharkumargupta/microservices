package com.trantor.learning.cartservice.dto;

import com.trantor.learning.cartservice.domain.Cart;

public interface Marshallable<I, O> {

    O to(I t);
    I from(O o);
}
