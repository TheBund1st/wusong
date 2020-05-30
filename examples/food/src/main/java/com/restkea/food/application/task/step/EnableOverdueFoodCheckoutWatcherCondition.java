package com.restkea.food.application.task.step;

import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import com.restkea.food.domain.order.FoodOrder;
import org.thebund1st.wusong.application.task.step.EnableOverdueCheckoutWatcherCondition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnableOverdueFoodCheckoutWatcherCondition implements
        EnableOverdueCheckoutWatcherCondition<PlaceFoodOrderContext, FoodOrder> {
    @Override
    public boolean satisfiedBy(FoodOrder returning, PlaceFoodOrderContext context) {
        return true;
    }

}
