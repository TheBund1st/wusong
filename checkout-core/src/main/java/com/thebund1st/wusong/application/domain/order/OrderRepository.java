package com.thebund1st.wusong.application.domain.order;

public interface OrderRepository<O> {
    void save(O order);
}
