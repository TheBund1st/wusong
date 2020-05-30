package com.restkea.food.adapter.test.acceptance

import com.restkea.food.application.checkout.CheckoutFoodCommandHandler
import com.restkea.food.application.command.MakeFoodPaymentCommand
import com.restkea.food.application.payment.MakeFoodPaymentCommandHandler
import com.restkea.food.domain.order.FoodOrder
import com.restkea.food.domain.order.overdue.Overdue
import com.restkea.food.domain.payment.FoodPayment
import com.thebund1st.wusong.application.payment.MakePaymentCommandHandlerAspect
import com.thebund1st.wusong.domain.order.OrderRepository
import io.restassured.RestAssured
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static com.restkea.food.application.checkout.command.CheckoutFoodCommandFixture.aCheckoutFoodCommand
import static com.restkea.food.application.payment.command.MakeFoodPaymentCommandFixture.aMakeFoodPaymentCommand
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("acceptance")
class CheckoutFoodAcceptanceTest extends Specification {
    @LocalServerPort
    int randomServerPort

    @Autowired
    private CheckoutFoodCommandHandler checkoutFoodCommandHandler

    @Autowired
    private MakePaymentCommandHandlerAspect<MakeFoodPaymentCommand, FoodPayment> makeFoodPaymentCommandHandler

    @Autowired
    private OrderRepository<String, FoodOrder> foodOrderOrderRepository

    def setup() {
        RestAssured.port = randomServerPort
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    def "customer places order for food"() {
        given: "I as a customer"
        def command = aCheckoutFoodCommand().build()

        when: "place an order for food"

        def order = checkoutFoodCommandHandler.handle(command)

        then: "the order is ready to pay"
        assert order

        and: "the order will be canceled if overdue"
        assert order.overdue == Overdue.ENABLED
    }

    def "customer makes a payment"() {
        given: "I as a customer"

        and: "I placed an order for food"
        def command = aCheckoutFoodCommand().build()
        def order = checkoutFoodCommandHandler.handle(command)

        when: "make payment for the food"
        command = aMakeFoodPaymentCommand().with(order).withAmount(order.getTotalAmount()).build()
        def payment = makeFoodPaymentCommandHandler.handle(command)
        order = foodOrderOrderRepository.shouldFindBy(payment.getOrderId())

        then: "the order is fully paid"
        assert order.balanced

        and: "the order will not be canceled if overdue"
        assert order.overdue == Overdue.CLOSED
    }
}