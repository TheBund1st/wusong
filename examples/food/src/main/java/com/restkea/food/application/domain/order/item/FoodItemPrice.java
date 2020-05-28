package com.restkea.food.application.domain.order.item;

import lombok.Data;

@Data
public class FoodItemPrice {
    private double regularPrice;
    private double memberPrice;
}
