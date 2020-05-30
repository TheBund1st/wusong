package com.thebund1st.wusong.application.checkout.overdue;

import com.thebund1st.wusong.application.payment.MakePaymentThen;
import com.thebund1st.wusong.domain.order.OrderRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PROTECTED;

@Slf4j
public abstract class CloseOverdueCheckoutWatcherTemplate<Cmd, P, OID, O>
        implements MakePaymentThen<Cmd, P> {

    @Getter(value = PROTECTED)
    @Setter
    private OrderRepository<OID, O> orderRepository;

    @Override
    public void handle(Cmd command, P Payment) {
        O order = orderRepository.shouldFindBy(toOrderId(command));
        if (shouldCloseOverdueCheckoutWatcherFor(order)) {
            closeOverdueCheckoutWatcherFor(command, order);
        }
    }

    @SuppressWarnings("WeakerAccess")
    protected abstract OID toOrderId(Cmd command);

    @SuppressWarnings("WeakerAccess")
    protected abstract boolean shouldCloseOverdueCheckoutWatcherFor(O order);

    @SuppressWarnings("WeakerAccess")
    abstract protected void closeOverdueCheckoutWatcherFor(Cmd command, O order);
}
