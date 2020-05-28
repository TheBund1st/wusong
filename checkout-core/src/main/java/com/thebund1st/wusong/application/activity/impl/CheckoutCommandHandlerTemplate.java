package com.thebund1st.wusong.application.activity.impl;

import com.thebund1st.wusong.application.task.CheckoutTask;
import com.thebund1st.wusong.application.activity.interceptor.CheckoutCommandPostHandler;
import com.thebund1st.wusong.application.activity.interceptor.CheckoutCommandPreHandler;
import com.thebund1st.wusong.application.domain.order.OrderFactory;
import com.thebund1st.wusong.application.domain.order.OrderRepository;

public class CheckoutCommandHandlerTemplate<Cmd, O> implements CheckoutTask<Cmd, O> {

    private OrderFactory<O> orderFactory;
    private OrderRepository<O> orderRepository;
    private CheckoutCommandPreHandler<Cmd, O> checkoutCommandPreHandler;
    private CheckoutCommandPostHandler<Cmd, O> checkoutCommandPostHandler;

    @Override
    public O handle(Cmd command) {
        O order = orderFactory.make();
        checkoutCommandPreHandler.handle(command, order);
        orderRepository.save(order);
        checkoutCommandPostHandler.handle(command, order);
        return order;
    }
}
