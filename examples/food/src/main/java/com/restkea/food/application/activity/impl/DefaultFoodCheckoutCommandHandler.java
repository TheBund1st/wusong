package com.restkea.food.application.activity.impl;

import com.restkea.food.application.activity.FoodCheckoutCommandHandler;
import com.restkea.food.application.domain.order.FoodOrder;
import com.restkea.food.application.domain.order.FoodOrderFactory;
import com.restkea.food.application.domain.order.FoodOrderRepository;
import com.restkea.food.application.domain.order.item.FoodItem;
import com.restkea.food.application.domain.order.item.FoodOrderLineItem;
import com.restkea.food.application.domain.pricing.FoodPrice;
import com.restkea.food.application.domain.pricing.FoodPriceCalculator;
import com.restkea.food.application.task.CheckoutFoodCommand;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class DefaultFoodCheckoutCommandHandler
        implements FoodCheckoutCommandHandler {
    private final FoodOrderFactory orderFactory;
    private final FoodOrderRepository orderRepository;
    private final FoodPriceCalculator foodPriceCalculator;

    @Override
    public FoodOrder handle(CheckoutFoodCommand command) {
        FoodOrder order = toOrder(command);
        calculatePrice(order, command.getItems());
        orderRepository.save(order);
        return order;
    }

    private void calculatePrice(FoodOrder order, List<FoodItem> items) {
        FoodPrice foodPrice = foodPriceCalculator.calculateWith(items);
        order.setTotalAmount(foodPrice.getSubtotal());
        order.setLineItems(items.stream().map(i -> {
            FoodOrderLineItem lineItem = new FoodOrderLineItem();
            lineItem.setItem(i);
            lineItem.setPrice(foodPrice.getItemPrices().get(i));
            return lineItem;
        }).collect(toList()));
    }

    private FoodOrder toOrder(CheckoutFoodCommand command) {
        FoodOrder order = orderFactory.make(command.getWhen());
        order.setCustomerId(command.getCustomerId());
        order.setStoreId(command.getStoreId());
        return order;
    }
}
