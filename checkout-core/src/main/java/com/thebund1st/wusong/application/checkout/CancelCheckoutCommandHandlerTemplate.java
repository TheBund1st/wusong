package com.thebund1st.wusong.application.checkout;

import com.thebund1st.wusong.application.CancelCheckoutCommandHandler;
import com.thebund1st.wusong.domain.order.OrderRepository;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

public abstract class CancelCheckoutCommandHandlerTemplate<Cmd, OID, O>
        implements CancelCheckoutCommandHandler<Cmd, O> {

    @Getter(value = PROTECTED)
    @Setter
    private OrderRepository<OID, O> orderRepository;

    @Getter(value = PROTECTED)
    @Setter
    private CancelCheckoutThen<Cmd, O> cancelCheckoutThen;

    @Override
    public O handle(Cmd command) {
        O order = orderRepository.shouldFindBy(toOrderId(command));
        doCancel(command, order);
        return order;
    }

    @SuppressWarnings("WeakerAccess")
    protected abstract void doCancel(Cmd command, O order);

    @SuppressWarnings("WeakerAccess")
    protected abstract OID toOrderId(Cmd command);

}
