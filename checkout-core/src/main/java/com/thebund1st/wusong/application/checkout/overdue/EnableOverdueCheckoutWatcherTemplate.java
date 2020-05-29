package com.thebund1st.wusong.application.checkout.overdue;

import com.thebund1st.wusong.application.checkout.CheckoutThen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class EnableOverdueCheckoutWatcherTemplate<Cmd, O>
        implements CheckoutThen<Cmd, O> {

    @Override
    public void handle(Cmd command, O order) {
        if (shouldEnableOverdueCheckoutWatcherFor(order)) {
            log.info("Overdue checkout watcher should be enabled for order [{}]", order);
            enableOverdueCheckoutWatcherFor(order);
        } else {
            log.info("Overdue checkout watcher is not enabled for order [{}]", order);
        }
    }

    @SuppressWarnings("WeakerAccess")
    protected boolean shouldEnableOverdueCheckoutWatcherFor(O order) {
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    abstract protected void enableOverdueCheckoutWatcherFor(O order);
}
