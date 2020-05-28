package com.restkea.food.application.domain.pricing;

import com.restkea.food.application.domain.order.item.FoodItem;

import java.util.List;

public interface FoodPriceCalculator {
    FoodPrice calculateWith(List<FoodItem> items);
}
