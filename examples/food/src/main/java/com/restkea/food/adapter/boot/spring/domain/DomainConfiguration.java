package com.restkea.food.adapter.boot.spring.domain;

import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.FoodOrderFactory;
import com.restkea.food.domain.order.FoodOrderRepository;
import com.restkea.food.domain.order.NoSuchFoodOrderException;
import com.restkea.food.domain.payment.FoodPayment;
import com.restkea.food.domain.payment.FoodPaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DomainConfiguration {

    @Bean
    public FoodOrderFactory foodOrderFactory() {
        return new FoodOrderFactory();
    }

    @Bean
    public FoodOrderRepository foodOrderRepository() {
        return new FoodOrderRepository() {
            private Map<String, FoodOrder> orders = new HashMap<>();

            @Override
            public void save(FoodOrder order) {
                orders.put(order.getOrderId(), order);
            }

            @Override
            public FoodOrder shouldFindBy(String orderId) {
                if (!orders.containsKey(orderId)) {
                    throw new NoSuchFoodOrderException(orderId);
                }
                return orders.get(orderId);
            }
        };
    }

    @Bean
    public FoodPaymentRepository foodPaymentRepository() {
        return new FoodPaymentRepository() {
            private Map<String, FoodPayment> payments = new HashMap<>();

            @Override
            public void save(FoodPayment payment) {
                payments.put(payment.getOrderId(), payment);
            }
        };
    }

}
