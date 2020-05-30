package com.restkea.food.domain.order

import com.restkea.food.domain.order.item.FoodItem

class FoodItemFixture {
    FoodItem target = new FoodItem()

    def withItemNo(String value) {
        target.setItemNo(value)
        this
    }

    def withQuantity(int value) {
        target.setQuantity(value)
        this
    }

    def build() {
        target
    }

    static def aFoodItem() {
        new FoodItemFixture()
                .withItemNo(UUID.randomUUID().toString().replace("-", ""))
                .withQuantity(1)
    }
}
