package com.restkea.food.domain.pricing

import com.restkea.food.domain.order.item.FoodItemPrice

class FoodItemPriceFixture {
    FoodItemPrice target = new FoodItemPrice()

    def withRegularPrice(double value) {
        target.setRegularPrice(value)
        this
    }

    def withMemberPrice(double value) {
        target.setMemberPrice(value)
        this
    }

    def build() {
        target
    }

    static def aFoodItemPrice() {
        new FoodItemPriceFixture()
                .withRegularPrice(10)
                .withMemberPrice(9.5)
    }
}
