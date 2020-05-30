package com.restkea.food.application.task.context;

import com.restkea.food.application.command.PlaceFoodOrderCommand;
import com.restkea.food.domain.pricing.FoodPrice;
import lombok.Data;

@Data
public class PlaceFoodOrderContext {
    private PlaceFoodOrderCommand command;
    private FoodPrice foodPriceSnapshot;
}
