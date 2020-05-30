package com.restkea.food.application.task.context;

import com.restkea.food.application.command.ReceiveFoodPaymentCommand;
import com.restkea.food.domain.order.FoodOrder;
import lombok.Data;

@Data
public class ReceiveFoodPaymentContext {
    private ReceiveFoodPaymentCommand command;
    private FoodOrder order;
}
