package com.thebund1st.wusong.application.task;

public interface CheckoutTask<Cmd, O> {
    O handle(Cmd command);
}
