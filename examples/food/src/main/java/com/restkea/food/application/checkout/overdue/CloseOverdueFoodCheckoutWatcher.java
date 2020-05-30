package com.restkea.food.application.checkout.overdue;

import com.restkea.food.application.command.MakeFoodPaymentCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.payment.FoodPayment;
import com.thebund1st.wusong.application.checkout.overdue.CloseOverdueCheckoutWatcherTemplate;

public class CloseOverdueFoodCheckoutWatcher extends
        CloseOverdueCheckoutWatcherTemplate<MakeFoodPaymentCommand, FoodPayment, String, FoodOrder> {

    @Override
    protected String toOrderId(MakeFoodPaymentCommand command) {
        return command.getOrderId();
    }

    @Override
    protected boolean shouldCloseOverdueCheckoutWatcherFor(FoodOrder order) {
        return order.isOverdueWatcherEnabled() && order.isBalanced();
    }

    @Override
    protected void closeOverdueCheckoutWatcherFor(MakeFoodPaymentCommand command, FoodOrder order) {
        order.closeOverdue(command.getWhen());
    }
}
