package com.restkea.food.domain.pricing;

import com.restkea.food.domain.order.item.FoodItem;

import java.util.List;

public interface FoodPriceCalculator {
    FoodPrice calculateWith(List<FoodItem> items);
}
