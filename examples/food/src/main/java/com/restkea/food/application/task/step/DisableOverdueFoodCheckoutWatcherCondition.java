package com.restkea.food.application.task.step;

import com.restkea.food.application.task.context.ReceiveFoodPaymentContext;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.payment.FoodPayment;
import org.thebund1st.wusong.application.task.step.then.DisableOverdueCheckoutWatcherCondition;

public class DisableOverdueFoodCheckoutWatcherCondition
        implements DisableOverdueCheckoutWatcherCondition<ReceiveFoodPaymentContext, FoodPayment> {

    @Override
    public boolean satisfiedBy(FoodPayment returning, ReceiveFoodPaymentContext context) {
        FoodOrder order = context.getOrder();
        return order.isOverdueWatcherEnabled() && order.isBalanced();
    }
}
