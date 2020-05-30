package com.restkea.food.domain.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckoutFoodBusinessIdentity {
    private String storeId;
    private LocalDateTime when;
}
