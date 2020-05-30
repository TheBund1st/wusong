package com.restkea.food.application.task.step;

import com.restkea.food.application.command.ReceiveFoodPaymentCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.FoodOrderRepository;
import com.restkea.food.domain.payment.FoodPayment;
import com.restkea.food.application.task.context.ReceiveFoodPaymentContext;
import org.thebund1st.tfb.task.step.When;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
public class ReceiveFoodPayment implements When<ReceiveFoodPaymentContext, FoodPayment> {
    private final FoodOrderRepository orderRepository;

    @Override
    public FoodPayment execute(ReceiveFoodPaymentContext context) {
        FoodOrder order = orderRepository.shouldFindBy(context.getCommand().getOrderId());
        FoodPayment payment = toPayment(context.getCommand());
        payment.applyTo(order);
        context.setOrder(order);
        return payment;
    }

    private FoodPayment toPayment(ReceiveFoodPaymentCommand command) {
        FoodPayment payment = new FoodPayment();
        payment.setPaymentId(UUID.randomUUID().toString().replace("-", ""));
        payment.setCreatedAt(command.getWhen());
        payment.setOrderId(command.getOrderId());
        payment.setDigitalPaymentContextId(command.getDigitalPaymentContextId());
        payment.setAmount(command.getAmount());
        return payment;
    }
}
