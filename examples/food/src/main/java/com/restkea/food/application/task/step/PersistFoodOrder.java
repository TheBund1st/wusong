package com.restkea.food.application.task.step;

import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.FoodOrderRepository;
import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import org.thebund1st.tfb.task.step.Then;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersistFoodOrder
        implements Then<PlaceFoodOrderContext, FoodOrder> {

    private final FoodOrderRepository orderRepository;

    @Override
    public void execute(FoodOrder order, PlaceFoodOrderContext context) {
        orderRepository.save(order);
    }
}
