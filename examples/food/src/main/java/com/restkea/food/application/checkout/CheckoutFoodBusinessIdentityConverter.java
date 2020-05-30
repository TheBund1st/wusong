package com.restkea.food.application.checkout;

import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.domain.order.CheckoutFoodBusinessIdentity;
import com.thebund1st.wusong.application.checkout.CheckoutBusinessIdentityConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckoutFoodBusinessIdentityConverter
        implements CheckoutBusinessIdentityConverter<CheckoutFoodCommand, CheckoutFoodBusinessIdentity> {


    @Override
    public CheckoutFoodBusinessIdentity from(CheckoutFoodCommand checkoutFoodCommand) {
        CheckoutFoodBusinessIdentity businessIdentity = new CheckoutFoodBusinessIdentity();
        businessIdentity.setStoreId(checkoutFoodCommand.getStoreId());
        businessIdentity.setWhen(checkoutFoodCommand.getWhen());
        return businessIdentity;
    }
}
