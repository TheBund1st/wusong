package com.restkea.food.domain.order;

public interface FoodOrderRepository {
    void save(FoodOrder order);

    FoodOrder shouldFindBy(String orderId);
}
