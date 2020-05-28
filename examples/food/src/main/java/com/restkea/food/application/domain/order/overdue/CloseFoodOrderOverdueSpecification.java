package com.restkea.food.application.domain.order.overdue;

import com.restkea.food.application.domain.order.FoodOrder;

public interface CloseFoodOrderOverdueSpecification {
    boolean satisfiedBy(FoodOrder order);
}
