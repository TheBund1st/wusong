package com.thebund1st.wusong.application.checkout;

public interface CheckoutThen<Cmd, O> {
    void handle(Cmd command, O order);
}
