package com.restkea.food.domain.order;

public class NoSuchFoodOrderException extends RuntimeException {
    public NoSuchFoodOrderException(String orderId) {
        super(String.format("No such food order with id [%s]", orderId));
    }
}
