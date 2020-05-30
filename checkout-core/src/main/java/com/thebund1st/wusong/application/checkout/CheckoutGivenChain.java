package com.thebund1st.wusong.application.checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutGivenChain<Cmd, O> implements CheckoutGiven<Cmd, O> {

    private List<CheckoutGiven<Cmd, O>> givens = new ArrayList<>();

    public void add(CheckoutGiven<Cmd, O> given) {
        givens.add(given);
    }

    @Override
    public void handle(Cmd command, O order) {
        givens.forEach(given -> given.handle(command, order));
    }
}
