package com.restkea.food.application.activity;

import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.task.CloseFoodOrderOverdueCommand;

public interface CloseFoodOrderOverdueCommandHandler {

    FoodOrder handle(CloseFoodOrderOverdueCommand command);

}
