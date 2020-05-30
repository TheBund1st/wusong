package com.restkea.food.application.checkout.command

import com.restkea.food.application.command.CheckoutFoodCommand

import java.time.LocalDateTime

import static com.restkea.food.domain.customer.CustomerFixture.aCustomer
import static com.restkea.food.domain.store.StoreFixture.aStore


class CheckoutFoodCommandFixture {
    private CheckoutFoodCommand target = new CheckoutFoodCommand()

    def withCustomerId(String value) {
        target.setCustomerId(value)
        this
    }

    def withStoreId(String value) {
        target.setStoreId(value)
        this
    }

    def withWhen(LocalDateTime value) {
        target.setWhen(value)
        this
    }

    def build() {
        target
    }

    static def aCheckoutFoodCommand() {
        new CheckoutFoodCommandFixture()
                .withCustomerId(aCustomer().build().getId())
                .withStoreId(aStore().build().getId())
                .withWhen(LocalDateTime.now())
    }
}
