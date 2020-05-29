package com.thebund1st.wusong.application;

public interface CloseOverdueCheckoutWatcherCommandHandler<Cmd, O> {

    O handle(Cmd command);

}
