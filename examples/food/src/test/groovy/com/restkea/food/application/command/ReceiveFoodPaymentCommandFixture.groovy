package com.restkea.food.application.command


import com.restkea.food.domain.order.FoodOrder

import java.time.LocalDateTime

import static com.restkea.food.domain.order.FoodOrderFixture.anOrder

class ReceiveFoodPaymentCommandFixture {
    private ReceiveFoodPaymentCommand target = new ReceiveFoodPaymentCommand()

    def withAmount(double value) {
        target.setAmount(value)
        this
    }

    def with(FoodOrder value) {
        target.setOrderId(value.getOrderId())
        this
    }

    def withDigitalPaymentContextId(String value) {
        target.setDigitalPaymentContextId(value)
        this
    }

    def withWhen(LocalDateTime value) {
        target.setWhen(value)
        this
    }

    def build() {
        target
    }

    static def aMakeFoodPaymentCommand() {
        new ReceiveFoodPaymentCommandFixture()
                .with(anOrder().build())
                .withAmount(20)
                .withDigitalPaymentContextId(UUID.randomUUID().toString().replace("-", ""))
                .withWhen(LocalDateTime.now())
    }
}
