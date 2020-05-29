package com.restkea.food.application.domain.order;

import com.thebund1st.wusong.application.domain.order.OrderFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class FoodOrderFactory implements OrderFactory<String, FoodOrder> {

    @Override
    public FoodOrder make(String businessIdentity, LocalDateTime when) {
        FoodOrder order = new FoodOrder();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        order.setCreatedAt(when);
        return order;
    }
}
