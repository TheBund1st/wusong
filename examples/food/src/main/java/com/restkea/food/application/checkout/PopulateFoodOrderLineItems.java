package com.restkea.food.application.checkout;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.item.FoodItem;
import com.restkea.food.domain.order.item.FoodOrderLineItem;
import com.thebund1st.wusong.application.checkout.CheckoutGiven;

import static java.util.stream.Collectors.toList;

public class PopulateFoodOrderLineItems implements CheckoutGiven<CheckoutFoodCommand, FoodOrder> {

    @Override
    public void handle(CheckoutFoodCommand command, FoodOrder order) {
        order.setLineItems(command.getItems().stream().map(this::toLine).collect(toList()));
    }

    private FoodOrderLineItem toLine(FoodItem item) {
        FoodOrderLineItem lineItem = new FoodOrderLineItem();
        lineItem.setItem(item);
        return lineItem;
    }
}
