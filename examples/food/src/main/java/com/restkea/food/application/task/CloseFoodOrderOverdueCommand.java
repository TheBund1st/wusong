package com.restkea.food.application.task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CloseFoodOrderOverdueCommand {
    private String orderId;
    private LocalDateTime when;
}
