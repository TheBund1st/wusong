package com.thebund1st.wusong.application.checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutThenChain<Cmd, O> implements CheckoutThen<Cmd, O> {

    private List<CheckoutThen<Cmd, O>> thens = new ArrayList<>();

    public void add(CheckoutThen<Cmd, O> then) {
        thens.add(then);
    }

    @Override
    public void handle(Cmd command, O order) {
        thens.forEach(then -> then.handle(command, order));
    }
}
