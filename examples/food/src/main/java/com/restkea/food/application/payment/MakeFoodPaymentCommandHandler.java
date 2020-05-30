package com.restkea.food.application.payment;

import com.restkea.food.application.command.MakeFoodPaymentCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.payment.FoodPayment;
import com.thebund1st.wusong.application.payment.MakePaymentCommandHandlerTemplate;

import java.util.UUID;

public class MakeFoodPaymentCommandHandler extends
        MakePaymentCommandHandlerTemplate<MakeFoodPaymentCommand, String, FoodOrder, String, FoodPayment> {

    @Override
    protected FoodPayment toPayment(MakeFoodPaymentCommand command) {
        FoodPayment payment = new FoodPayment();
        payment.setPaymentId(UUID.randomUUID().toString().replace("-", ""));
        payment.setCreatedAt(command.getWhen());
        payment.setOrderId(command.getOrderId());
        payment.setDigitalPaymentContextId(command.getDigitalPaymentContextId());
        payment.setAmount(command.getAmount());
        return payment;
    }

    @Override
    protected String toOrderId(MakeFoodPaymentCommand command) {
        return command.getOrderId();
    }
}
