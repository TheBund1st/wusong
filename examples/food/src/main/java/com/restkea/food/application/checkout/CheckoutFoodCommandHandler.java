package com.restkea.food.application.checkout;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.CheckoutFoodBusinessIdentity;
import com.restkea.food.domain.order.FoodOrder;
import com.thebund1st.wusong.application.checkout.CheckoutCommandHandlerTemplate;

public class CheckoutFoodCommandHandler
        extends CheckoutCommandHandlerTemplate<CheckoutFoodCommand, CheckoutFoodBusinessIdentity, String, FoodOrder> {
}
