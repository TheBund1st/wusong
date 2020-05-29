package com.restkea.food.application.payment;

import com.restkea.food.application.command.MakeFoodOrderPaymentCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.payment.FoodPayment;
import com.thebund1st.wusong.application.payment.MakePaymentCommandHandlerTemplate;

import java.util.UUID;

public class MakeFoodPaymentCommandHandler extends
        MakePaymentCommandHandlerTemplate<MakeFoodOrderPaymentCommand, String, FoodOrder, String, FoodPayment> {

    @Override
    protected FoodPayment toPayment(MakeFoodOrderPaymentCommand command) {
        FoodPayment payment = new FoodPayment();
        payment.setPaymentId(UUID.randomUUID().toString().replace("-", ""));
        payment.setCreatedAt(command.getWhen());
        payment.setOrderId(command.getOrderId());
        payment.setDigitalPaymentContextId(command.getDigitalPaymentContextId());
        payment.setAmount(command.getAmount());
        return payment;
    }

    @Override
    protected String toOrderId(MakeFoodOrderPaymentCommand command) {
        return command.getOrderId();
    }
}
