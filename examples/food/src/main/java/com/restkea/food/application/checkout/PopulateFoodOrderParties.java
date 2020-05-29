package com.restkea.food.application.checkout;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.thebund1st.wusong.application.checkout.CheckoutGiven;

public class PopulateFoodOrderParties implements CheckoutGiven<CheckoutFoodCommand, FoodOrder> {

    @Override
    public void handle(CheckoutFoodCommand command, FoodOrder order) {
        order.setCustomerId(command.getCustomerId());
        order.setStoreId(command.getStoreId());
    }
}
