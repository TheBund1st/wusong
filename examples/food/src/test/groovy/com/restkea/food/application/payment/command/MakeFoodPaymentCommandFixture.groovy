package com.restkea.food.application.payment.command

import com.restkea.food.application.command.MakeFoodPaymentCommand
import com.restkea.food.domain.order.FoodOrder

import java.time.LocalDateTime

import static com.restkea.food.domain.order.FoodOrderFixture.anOrder

class MakeFoodPaymentCommandFixture {
    private MakeFoodPaymentCommand target = new MakeFoodPaymentCommand()

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
        new MakeFoodPaymentCommandFixture()
                .with(anOrder().build())
                .withAmount(20)
                .withDigitalPaymentContextId(UUID.randomUUID().toString().replace("-", ""))
                .withWhen(LocalDateTime.now())
    }
}
