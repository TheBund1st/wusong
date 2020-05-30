package com.restkea.food.domain.order;

import java.time.LocalDateTime;
import java.util.UUID;

public class FoodOrderFactory {

    public FoodOrder make(LocalDateTime when) {
        FoodOrder order = new FoodOrder();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        order.setCreatedAt(when);
        return order;
    }
}
