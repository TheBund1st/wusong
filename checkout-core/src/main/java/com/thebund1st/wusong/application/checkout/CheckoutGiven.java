package com.thebund1st.wusong.application.checkout;

public interface CheckoutGiven<Cmd, O> {
    void handle(Cmd command, O order);
}
