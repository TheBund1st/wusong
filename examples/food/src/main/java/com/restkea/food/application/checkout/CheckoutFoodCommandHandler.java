package com.restkea.food.application.checkout;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.thebund1st.wusong.application.checkout.CheckoutCommandHandlerTemplate;

import java.time.LocalDateTime;

public class CheckoutFoodCommandHandler
        extends CheckoutCommandHandlerTemplate<CheckoutFoodCommand, String, String, FoodOrder> {

    @Override
    protected LocalDateTime toCreatedAt(CheckoutFoodCommand command) {
        return command.getWhen();
    }

    @Override
    protected String toBusinessIdentity(CheckoutFoodCommand command) {
        return ""; // No need in this case
    }
}
