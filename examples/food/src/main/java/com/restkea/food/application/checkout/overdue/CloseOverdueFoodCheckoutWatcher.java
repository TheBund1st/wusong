package com.restkea.food.application.checkout.overdue;

import com.restkea.food.application.command.CloseFoodOverdueCheckoutCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.thebund1st.wusong.application.checkout.overdue.CloseOverdueCheckoutWatcherTemplate;

public class CloseOverdueFoodCheckoutWatcher extends
        CloseOverdueCheckoutWatcherTemplate<CloseFoodOverdueCheckoutCommand, String, FoodOrder> {

    @Override
    protected String toOrderId(CloseFoodOverdueCheckoutCommand command) {
        return command.getOrderId();
    }

    @Override
    protected boolean shouldCloseOverdueCheckoutWatcherFor(FoodOrder order) {
        return order.isOverdueWatcherEnabled() && order.isBalanced();
    }

    @Override
    protected void closeOverdueCheckoutWatcherFor(CloseFoodOverdueCheckoutCommand command, FoodOrder order) {
        order.closeOverdue(command.getWhen());
    }
}
