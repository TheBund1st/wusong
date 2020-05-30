package com.restkea.food.application.command

import com.restkea.food.domain.order.item.FoodItem

import java.time.LocalDateTime

import static com.restkea.food.domain.customer.CustomerFixture.aCustomer
import static com.restkea.food.domain.order.FoodItemFixture.aFoodItem
import static com.restkea.food.domain.store.StoreFixture.aStore


class PlaceFoodOrderCommandFixture {
    private PlaceFoodOrderCommand target = new PlaceFoodOrderCommand()

    def withCustomerId(String value) {
        target.setCustomerId(value)
        this
    }

    def withStoreId(String value) {
        target.setStoreId(value)
        this
    }

    def butWith(FoodItem value) {
        target.setItems(new ArrayList<FoodItem>())
        append(value)
    }

    def append(FoodItem value) {
        target.getItems().add(value)
        this
    }

    def withWhen(LocalDateTime value) {
        target.setWhen(value)
        this
    }

    def build() {
        target
    }

    static def aPlaceFoodOrderCommand() {
        new PlaceFoodOrderCommandFixture()
                .withCustomerId(aCustomer().build().getId())
                .withStoreId(aStore().build().getId())
                .append(aFoodItem().build())
                .withWhen(LocalDateTime.now())
    }
}
