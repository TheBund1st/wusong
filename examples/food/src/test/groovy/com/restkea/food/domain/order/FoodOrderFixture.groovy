package com.restkea.food.domain.order

class FoodOrderFixture {
    FoodOrder target = new FoodOrder()

    def withOrderId(String value) {
        target.setOrderId(value)
        this
    }

    def build() {
        target
    }

    static def anOrder() {
        new FoodOrderFixture()
                .withOrderId(UUID.randomUUID().toString().replace("-", ""))
    }
}
