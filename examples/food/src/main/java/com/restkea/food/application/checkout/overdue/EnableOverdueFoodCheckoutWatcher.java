package com.restkea.food.application.checkout.overdue;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.overdue.Overdue;
import com.thebund1st.wusong.application.checkout.overdue.EnableOverdueCheckoutWatcherTemplate;
import com.thebund1st.wusong.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnableOverdueFoodCheckoutWatcher extends
        EnableOverdueCheckoutWatcherTemplate<CheckoutFoodCommand, FoodOrder> {

    private final OrderRepository<String, FoodOrder> orderRepository;

    @Override
    protected void enableOverdueCheckoutWatcherFor(FoodOrder order) {
        FoodOrder o = orderRepository.shouldFindBy(order.getOrderId());
        o.setOverdue(Overdue.ENABLED);
    }
}
