package com.restkea.food.application.domain.order.overdue;

import com.restkea.food.application.domain.order.FoodOrder;

public interface CancelFoodOrderOverdueSpecification {
    boolean satisfiedBy(FoodOrder order);
}
