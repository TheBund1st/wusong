package com.restkea.food.application.task.command;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CloseFoodOverdueCheckoutCommand {
    private String orderId;
    private LocalDateTime when;
}
