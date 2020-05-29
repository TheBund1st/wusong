package com.restkea.food.application.command;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CancelFoodCheckoutCommand {
    private String orderId;
    private LocalDateTime when;
}
