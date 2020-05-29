package com.thebund1st.wusong.application;

public interface CancelCheckoutCommandHandler<Cmd, O> {
    O handle(Cmd command);
}
