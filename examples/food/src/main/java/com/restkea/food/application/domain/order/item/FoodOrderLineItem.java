package com.restkea.food.application.domain.order.item;

import lombok.Data;

@Data
public class FoodOrderLineItem {
    private FoodItem item;
    private FoodItemPrice price;
}
