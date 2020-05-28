package com.restkea.food.application.activity;

import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.task.CheckoutFoodCommand;

public interface FoodCheckoutCommandHandler {

    FoodOrder handle(CheckoutFoodCommand command);

}
