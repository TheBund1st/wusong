package com.restkea.food.application.command;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MakeFoodOrderPaymentCommand {
    private String orderId;
    private String digitalPaymentContextId;
    private double amount;
    private LocalDateTime when;
}
