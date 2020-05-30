package com.restkea.food.domain.store

class StoreFixture {
    String id

    def getId() {
        id
    }

    def build() {
        this
    }

    static def aStore() {
        def fixture = new StoreFixture()
        fixture.id = UUID.randomUUID().toString().replace("-", "")
        fixture
    }
}
