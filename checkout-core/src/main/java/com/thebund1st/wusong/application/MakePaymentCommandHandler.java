package com.thebund1st.wusong.application;

public interface MakePaymentCommandHandler<Cmd, P> {
    P handle(Cmd command);
}
