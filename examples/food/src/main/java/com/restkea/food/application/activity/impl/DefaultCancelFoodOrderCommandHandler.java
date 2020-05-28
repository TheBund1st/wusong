package com.restkea.food.application.activity.impl;

import com.restkea.food.application.activity.CancelFoodOrderCommandHandler;
import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.domain.order.FoodOrderRepository;
import com.restkea.food.application.domain.order.overdue.CancelFoodOrderOverdueSpecification;
import com.restkea.food.application.task.CancelFoodOrderCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCancelFoodOrderCommandHandler
        implements CancelFoodOrderCommandHandler {
    private final FoodOrderRepository orderRepository;
    private final CancelFoodOrderOverdueSpecification orderOverdueSpecification;

    @Override
    public FoodOrder handle(CancelFoodOrderCommand command) {
        FoodOrder order = orderRepository.shouldFindBy(command.getOrderId());
        if (orderOverdueSpecification.satisfiedBy(order)) {
            order.cancel(command.getWhen());
        }
        return order;
    }
}
