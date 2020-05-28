package com.restkea.food.application.activity;

import com.restkea.food.application.domain.payment.FoodOrderPayment;
import com.restkea.food.application.task.MakeFoodOrderPaymentCommand;

public interface MakeFoodOrderPaymentCommandHandler {

    FoodOrderPayment handle(MakeFoodOrderPaymentCommand command);

}
