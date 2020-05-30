package com.thebund1st.wusong.application.payment;

import java.util.ArrayList;
import java.util.List;

public class MakePaymentThenChain<Cmd, P> implements MakePaymentThen<Cmd, P> {

    private List<MakePaymentThen<Cmd, P>> thens = new ArrayList<>();

    public void add(MakePaymentThen<Cmd, P> then) {
        thens.add(then);
    }

    @Override
    public void handle(Cmd command, P payment) {
        thens.forEach(then -> then.handle(command, payment));
    }
}
