package com.restkea.food.application.task.step;

import com.restkea.food.domain.payment.FoodPayment;
import com.restkea.food.application.task.context.ReceiveFoodPaymentContext;
import org.thebund1st.tfb.task.step.Then;
import com.restkea.food.domain.payment.FoodPaymentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersistFoodPayment
        implements Then<ReceiveFoodPaymentContext, FoodPayment> {

    private final FoodPaymentRepository foodPaymentRepository;

    @Override
    public void execute(FoodPayment order, ReceiveFoodPaymentContext context) {
        foodPaymentRepository.save(order);
    }
}
