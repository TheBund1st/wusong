package com.restkea.food.application.domain.pricing;

import com.restkea.food.application.domain.order.item.FoodItem;
import com.restkea.food.application.domain.order.item.FoodItemPrice;
import lombok.Data;

import java.util.Map;

@Data
public class FoodPrice {
    private Map<FoodItem, FoodItemPrice> itemPrices;
    private double subtotal;
}
