package com.restkea.food.domain.pricing

import com.restkea.food.domain.order.item.FoodItem
import com.restkea.food.domain.order.item.FoodItemPrice

import static com.restkea.food.domain.order.FoodItemFixture.aFoodItem
import static com.restkea.food.domain.pricing.FoodItemPriceFixture.aFoodItemPrice

class FoodPriceFixture {
    FoodPrice target

    FoodPriceFixture() {
        this.target = new FoodPrice()
        this.target.setItemPrices(new HashMap<FoodItem, FoodItemPrice>())
    }

    def withSubtotal(double value) {
        target.setSubtotal(value)
        this
    }

    def withItemPrice(FoodItem key, FoodItemPrice value) {
        target.getItemPrices().put(key, value)
        this
    }

    def build() {
        target
    }

    static def aFoodPrice() {
        def price = aFoodItemPrice().build()
        new FoodPriceFixture()
                .withItemPrice(aFoodItem().build(), price)
                .withSubtotal(price.regularPrice)
    }
}
