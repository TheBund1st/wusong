package com.thebund1st.wusong.domain.order;

public interface ApplyToOrder<O> {
    void applyTo(O order);
}
