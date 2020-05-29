package com.restkea.food.application.task.checkout;

import com.restkea.food.application.task.command.CancelFoodCheckoutCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.thebund1st.wusong.application.checkout.CancelCheckoutCommandHandlerTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CancelFoodCheckoutCommandHandler
        extends CancelCheckoutCommandHandlerTemplate<CancelFoodCheckoutCommand, String, FoodOrder> {

    @Override
    protected void doCancel(CancelFoodCheckoutCommand command, FoodOrder order) {
        order.cancel(command.getWhen());
    }

    @Override
    protected String toOrderId(CancelFoodCheckoutCommand command) {
        return command.getOrderId();
    }
}
