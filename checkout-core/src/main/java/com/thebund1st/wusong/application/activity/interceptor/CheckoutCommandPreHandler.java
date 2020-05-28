package com.thebund1st.wusong.application.activity.interceptor;

public interface CheckoutCommandPreHandler<Cmd, O> {
    void handle(Cmd command, O order);
}
