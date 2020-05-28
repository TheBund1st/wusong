package com.thebund1st.wusong.application.domain.order;

public interface ApplyToOrder<O> {
    void applyTo(O order);
}
