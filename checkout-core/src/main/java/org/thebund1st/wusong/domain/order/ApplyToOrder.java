package org.thebund1st.wusong.domain.order;

public interface ApplyToOrder<O> {
    void applyTo(O order);
}
