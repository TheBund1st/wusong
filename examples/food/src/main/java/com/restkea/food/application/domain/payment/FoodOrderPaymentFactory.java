package com.restkea.food.application.domain.payment;

import java.time.LocalDateTime;

public interface FoodOrderPaymentFactory {
    FoodOrderPayment make(LocalDateTime when);
}
