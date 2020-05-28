package com.restkea.food.application.activity.impl;

import com.restkea.food.application.activity.MakeFoodOrderPaymentCommandHandler;
import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.domain.order.FoodOrderRepository;
import com.restkea.food.application.domain.payment.FoodOrderPayment;
import com.restkea.food.application.domain.payment.FoodOrderPaymentFactory;
import com.restkea.food.application.domain.payment.FoodOrderPaymentRepository;
import com.restkea.food.application.task.MakeFoodOrderPaymentCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultMakeFoodOrderPaymentCommandHandler
        implements MakeFoodOrderPaymentCommandHandler {
    private final FoodOrderRepository orderRepository;
    private final FoodOrderPaymentFactory paymentFactory;
    private final FoodOrderPaymentRepository paymentRepository;

    @Override
    public FoodOrderPayment handle(MakeFoodOrderPaymentCommand command) {
        FoodOrder foodOrder = orderRepository.shouldFindBy(command.getOrderId());
        FoodOrderPayment orderPayment = toPayment(command);
        foodOrder.getPaidWith(orderPayment.getAmount());
        paymentRepository.save(orderPayment);
        return orderPayment;
    }

    private FoodOrderPayment toPayment(MakeFoodOrderPaymentCommand command) {
        FoodOrderPayment orderPayment = paymentFactory.make(command.getWhen());
        orderPayment.setOrderId(command.getOrderId());
        orderPayment.setDigitalPaymentContextId(command.getDigitalPaymentContextId());
        orderPayment.setAmount(command.getAmount());
        return orderPayment;
    }
}
