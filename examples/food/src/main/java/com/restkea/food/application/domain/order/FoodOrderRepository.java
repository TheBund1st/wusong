package com.restkea.food.application.domain.order;

public interface FoodOrderRepository {
    void save(FoodOrder order);

    FoodOrder shouldFindBy(String orderId);
}
