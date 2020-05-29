package com.thebund1st.wusong.application.checkout;

import com.thebund1st.wusong.domain.order.OrderFactory;
import com.thebund1st.wusong.domain.order.OrderRepository;
import com.thebund1st.wusong.application.CheckoutCommandHandler;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

public abstract class CheckoutCommandHandlerTemplate<Cmd, BID, OID, O> implements CheckoutCommandHandler<Cmd, O> {

    @Getter(value = PROTECTED)
    @Setter
    private OrderFactory<BID, O> orderFactory;

    @Getter(value = PROTECTED)
    @Setter
    private OrderRepository<OID, O> orderRepository;

    @Getter(value = PROTECTED)
    @Setter
    private CheckoutGiven<Cmd, O> checkoutGiven;

    @Getter(value = PROTECTED)
    @Setter
    private CheckoutThen<Cmd, O> checkoutThen;

    @Override
    public O handle(Cmd command) {
        O order = orderFactory.make(toBusinessIdentity(command), toCreatedAt(command));
        checkoutGiven.handle(command, order);
        orderRepository.save(order);
        checkoutThen.handle(command, order);
        return order;
    }

    @SuppressWarnings("WeakerAccess")
    protected abstract BID toBusinessIdentity(Cmd command);

    @SuppressWarnings("WeakerAccess")
    protected abstract LocalDateTime toCreatedAt(Cmd command);
}
