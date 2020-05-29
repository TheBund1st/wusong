package com.restkea.food.application.checkout;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.item.FoodOrderLineItem;
import com.restkea.food.domain.pricing.FoodPrice;
import com.restkea.food.domain.pricing.FoodPriceCalculator;
import com.thebund1st.wusong.application.checkout.CheckoutGiven;
import lombok.RequiredArgsConstructor;


/**
 * This should happen after {@link PopulateFoodOrderLineItems}
 */
@RequiredArgsConstructor
public class SnapshotFoodPrice implements CheckoutGiven<CheckoutFoodCommand, FoodOrder> {
    private final FoodPriceCalculator foodPriceCalculator;

    @Override
    public void handle(CheckoutFoodCommand command, FoodOrder order) {
        FoodPrice foodPrice = foodPriceCalculator.calculateWith(command.getItems());
        order.setTotalAmount(foodPrice.getSubtotal());
        order.getLineItems().forEach(lineItem -> assignItemPrice(lineItem, foodPrice));
    }

    private void assignItemPrice(FoodOrderLineItem lineItem, FoodPrice foodPrice) {
        // assuming every item can get its price
        lineItem.setPrice(foodPrice.getItemPrices().get(lineItem.getItem()));
    }
}
