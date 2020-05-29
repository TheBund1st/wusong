package com.thebund1st.wusong.application.checkout;

public interface CancelCheckoutThen<Cmd, O> {
    void handle(Cmd command, O order);
}
