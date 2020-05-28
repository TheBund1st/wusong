package com.restkea.food.application.activity;

import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.task.CancelFoodOrderCommand;

public interface CancelFoodOrderCommandHandler {

    FoodOrder handle(CancelFoodOrderCommand command);

}
