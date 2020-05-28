package com.restkea.food.application.domain.order;

import java.time.LocalDateTime;

public interface FoodOrderFactory {
    FoodOrder make(LocalDateTime when);
}
