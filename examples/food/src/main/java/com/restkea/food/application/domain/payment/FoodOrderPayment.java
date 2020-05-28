package com.restkea.food.application.domain.payment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(of = "paymentId")
@ToString(of = "paymentId")
public class FoodOrderPayment {
    private String paymentId;
    private String orderId;
    private String digitalPaymentContextId;
    private double amount;
    private LocalDateTime createdAt;
}
