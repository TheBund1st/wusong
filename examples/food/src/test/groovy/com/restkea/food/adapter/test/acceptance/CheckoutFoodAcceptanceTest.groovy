package com.restkea.food.adapter.test.acceptance


import com.restkea.food.application.task.context.PlaceFoodOrderContext
import com.restkea.food.application.task.context.ReceiveFoodPaymentContext
import com.restkea.food.domain.order.FoodOrder
import com.restkea.food.domain.order.FoodOrderRepository
import com.restkea.food.domain.order.overdue.Overdue
import com.restkea.food.domain.payment.FoodPayment
import com.restkea.food.domain.pricing.FoodPriceCalculator
import org.thebund1st.tfb.task.Task
import io.restassured.RestAssured
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static com.restkea.food.application.command.PlaceFoodOrderCommandFixture.aPlaceFoodOrderCommand
import static com.restkea.food.application.command.ReceiveFoodPaymentCommandFixture.aMakeFoodPaymentCommand
import static com.restkea.food.domain.order.FoodItemFixture.aFoodItem
import static com.restkea.food.domain.pricing.FoodItemPriceFixture.aFoodItemPrice
import static com.restkea.food.domain.pricing.FoodPriceFixture.aFoodPrice
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("acceptance")
class CheckoutFoodAcceptanceTest extends Specification {
    @LocalServerPort
    int randomServerPort

    @Autowired
    private Task<PlaceFoodOrderContext, FoodOrder> placeFoodOrderTask

    @Autowired
    private Task<ReceiveFoodPaymentContext, FoodPayment> receiveFoodPaymentTask

    @Autowired
    private FoodOrderRepository foodOrderRepository

    @SpringBean
    private FoodPriceCalculator foodPriceCalculator = Mock()

    def setup() {
        RestAssured.port = randomServerPort
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    def "customer places order for food"() {
        given: "I as a customer"

        and: "I want to order some food"
        def item = aFoodItem().build()
        def foodPrice = aFoodPrice().withItemPrice(item, aFoodItemPrice().build()).build()
        foodPriceCalculator.calculateWith(item.iterator().toList()) >> foodPrice

        when: "place an order for food"
        def command = aPlaceFoodOrderCommand().butWith(item).build()
        def context = new PlaceFoodOrderContext()
        context.setCommand(command)
        def order = placeFoodOrderTask.execute(context)

        then: "the order is ready to pay"
        assert order

        and: "the order will be canceled if overdue"
        assert order.overdue == Overdue.ENABLED
    }

    def "customer makes a payment"() {
        given: "I as a customer"

        and: "I placed an order for food"
        def item = aFoodItem().build()
        def foodPrice = aFoodPrice().withItemPrice(item, aFoodItemPrice().build()).build()
        foodPriceCalculator.calculateWith(item.iterator().toList()) >> foodPrice
        def command = aPlaceFoodOrderCommand().butWith(item).build()
        def context = new PlaceFoodOrderContext()
        context.setCommand(command)
        def order = placeFoodOrderTask.execute(context)

        when: "make payment for the food"
        command = aMakeFoodPaymentCommand().with(order).withAmount(order.getTotalAmount()).build()
        context = new ReceiveFoodPaymentContext()
        context.setCommand(command)
        def payment = receiveFoodPaymentTask.execute(context)
        order = foodOrderRepository.shouldFindBy(payment.getOrderId())

        then: "the order is fully paid"
        assert order.balanced

        and: "the order will not be canceled if overdue"
        assert order.overdue == Overdue.CLOSED
    }
}