package com.restkea.food.application.activity.impl;

import com.restkea.food.application.activity.CloseFoodOrderOverdueCommandHandler;
import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.domain.order.FoodOrderRepository;
import com.restkea.food.application.domain.order.overdue.CloseFoodOrderOverdueSpecification;
import com.restkea.food.application.task.CloseFoodOrderOverdueCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCloseFoodOrderOverdueCommandHandler
        implements CloseFoodOrderOverdueCommandHandler {
    private final FoodOrderRepository orderRepository;
    private final CloseFoodOrderOverdueSpecification orderOverdueSpecification;

    @Override
    public FoodOrder handle(CloseFoodOrderOverdueCommand command) {
        FoodOrder order = orderRepository.shouldFindBy(command.getOrderId());
        if (orderOverdueSpecification.satisfiedBy(order)) {
            order.closeOverdue(command.getWhen());
        }
        return order;
    }
}
