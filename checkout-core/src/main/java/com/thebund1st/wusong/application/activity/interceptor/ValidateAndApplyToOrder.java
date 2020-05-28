package com.thebund1st.wusong.application.activity.interceptor;

import com.thebund1st.wusong.application.activity.interceptor.CheckoutCommandPreHandler;
import com.thebund1st.wusong.application.domain.order.ApplyToOrder;

public interface ValidateAndApplyToOrder<Cmd, O> extends CheckoutCommandPreHandler<Cmd, O> {

    @Override
    default void handle(Cmd command, O order) {
        ApplyToOrder<O> entity = extractFrom(command);
        entity.applyTo(order);
    }

    ApplyToOrder<O> extractFrom(Cmd o);
}
