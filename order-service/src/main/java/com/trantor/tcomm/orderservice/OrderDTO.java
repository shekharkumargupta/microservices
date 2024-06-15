package com.trantor.tcomm.orderservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Integer id;
    private Integer cart;
    private String email;
    private String address;
    private boolean active;

}
