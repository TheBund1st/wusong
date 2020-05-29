package com.restkea.food.application.domain.payment;

import com.restkea.food.application.domain.order.FoodOrder;
import com.thebund1st.wusong.application.domain.order.ApplyToOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@EqualsAndHashCode(of = "paymentId")
@ToString(of = "paymentId")
public class FoodPayment implements ApplyToOrder<FoodOrder> {
    private String paymentId;
    private String orderId;
    private String digitalPaymentContextId;
    private double amount;
    private LocalDateTime createdAt;

    @Override
    public void applyTo(FoodOrder order) {
        order.getPaidWith(getAmount());
    }
}
