package com.restkea.food.domain.customer

class CustomerFixture {
    String id

    def getId() {
        id
    }

    def build() {
        this
    }

    static def aCustomer() {
        def fixture = new CustomerFixture()
        fixture.id = UUID.randomUUID().toString().replace("-", "")
        fixture
    }
}
