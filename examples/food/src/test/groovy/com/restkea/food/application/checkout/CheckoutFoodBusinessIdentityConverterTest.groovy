package com.restkea.food.application.checkout

import spock.lang.Specification

import static com.restkea.food.application.checkout.command.CheckoutFoodCommandFixture.aCheckoutFoodCommand

class CheckoutFoodBusinessIdentityConverterTest extends Specification {

    CheckoutFoodBusinessIdentityConverter subject

    def setup() {
        subject = new CheckoutFoodBusinessIdentityConverter()
    }

    def "it should convert CheckoutFoodCommand to CheckoutFoodBusinessIdentity"() {
        given:
        def command = aCheckoutFoodCommand().build()

        when:
        def actual = subject.from(command)

        then:
        assert actual.storeId == command.storeId
        assert actual.when == command.when
    }
}
