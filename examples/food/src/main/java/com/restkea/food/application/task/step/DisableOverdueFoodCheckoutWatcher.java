package com.restkea.food.application.task.step;

import com.restkea.food.application.command.ReceiveFoodPaymentCommand;
import com.restkea.food.application.task.context.ReceiveFoodPaymentContext;
import com.restkea.food.domain.payment.FoodPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thebund1st.wusong.application.task.step.then.DisableOverdueCheckoutWatcher;

@Slf4j
@RequiredArgsConstructor
public class DisableOverdueFoodCheckoutWatcher
        implements DisableOverdueCheckoutWatcher<ReceiveFoodPaymentContext, FoodPayment> {

    @Override
    public void execute(FoodPayment payment, ReceiveFoodPaymentContext context) {
        ReceiveFoodPaymentCommand command = context.getCommand();
        context.getOrder().closeOverdue(command.getWhen());
    }

}
