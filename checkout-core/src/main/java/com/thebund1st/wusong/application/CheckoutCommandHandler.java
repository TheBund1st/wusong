package com.thebund1st.wusong.application;

public interface CheckoutCommandHandler<Cmd, O> {
    O handle(Cmd command);
}
