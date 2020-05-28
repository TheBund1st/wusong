package com.thebund1st.wusong.application.activity.interceptor;

public interface CheckoutCommandPostHandler<Cmd, O> {
    void handle(Cmd command, O order);
}
