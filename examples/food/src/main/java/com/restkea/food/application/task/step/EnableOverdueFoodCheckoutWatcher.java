package com.restkea.food.application.task.step;

import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.overdue.Overdue;
import org.thebund1st.wusong.application.task.step.then.EnableOverdueCheckoutWatcher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnableOverdueFoodCheckoutWatcher implements
        EnableOverdueCheckoutWatcher<PlaceFoodOrderContext, FoodOrder> {

    @Override
    public void execute(FoodOrder order, PlaceFoodOrderContext context) {
        order.setOverdue(Overdue.ENABLED);
    }
}
