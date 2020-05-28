package com.thebund1st.wusong.application.activity.impl;

import com.thebund1st.wusong.application.activity.interceptor.CheckoutCommandPreHandler;

import java.util.ArrayList;
import java.util.List;

public class CheckoutCommandPreHandlerChainTemplate<Cmd, O> implements CheckoutCommandPreHandler<Cmd, O> {

    private final List<CheckoutCommandPreHandler<Cmd, O>> preHandlers = new ArrayList<>();

    @Override
    public void handle(Cmd command, O order) {
        preHandlers.forEach(handler -> handler.handle(command, order));
    }
}
