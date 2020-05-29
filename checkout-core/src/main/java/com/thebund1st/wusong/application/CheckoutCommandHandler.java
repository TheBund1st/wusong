package com.thebund1st.wusong.application.task;

public interface CheckoutCommandHandler<Cmd, O> {
    O handle(Cmd command);
}
