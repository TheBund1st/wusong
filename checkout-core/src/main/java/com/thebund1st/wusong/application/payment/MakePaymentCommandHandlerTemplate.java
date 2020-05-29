package com.thebund1st.wusong.application.payment;

import com.thebund1st.wusong.domain.order.ApplyToOrder;
import com.thebund1st.wusong.domain.order.OrderRepository;
import com.thebund1st.wusong.domain.payment.PaymentRepository;
import com.thebund1st.wusong.application.MakePaymentCommandHandler;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

public abstract class MakePaymentCommandHandlerTemplate<Cmd, OID, O, PID, P extends ApplyToOrder<O>>
        implements MakePaymentCommandHandler<Cmd, P> {

    @Getter(value = PROTECTED)
    @Setter
    private OrderRepository<OID, O> orderRepository;

    @Getter(value = PROTECTED)
    @Setter
    private PaymentRepository<PID, P> paymentRepository;

    @Override
    public P handle(Cmd command) {
        O order = orderRepository.shouldFindBy(toOrderId(command));
        P payment = toPayment(command);
        payment.applyTo(order);
        paymentRepository.save(payment);
        return payment;
    }


    @SuppressWarnings("WeakerAccess")
    protected abstract P toPayment(Cmd command);

    @SuppressWarnings("WeakerAccess")
    protected abstract OID toOrderId(Cmd command);

}
