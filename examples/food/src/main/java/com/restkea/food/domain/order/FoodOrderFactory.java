package com.restkea.food.domain.order;

import com.thebund1st.wusong.domain.order.OrderFactory;

import java.util.UUID;

public class FoodOrderFactory implements OrderFactory<CheckoutFoodBusinessIdentity, FoodOrder> {

    @Override
    public FoodOrder make(CheckoutFoodBusinessIdentity businessIdentity) {
        FoodOrder order = new FoodOrder();
        order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
        order.setCreatedAt(businessIdentity.getWhen());
        return order;
    }
}
