package com.thebund1st.wusong.application.payment;

public interface MakePaymentThen<Cmd, P> {
    void handle(Cmd command, P payment);
}
