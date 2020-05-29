package com.thebund1st.wusong.domain.payment;

public interface PaymentRepository<PID, P> {
    void save(P payment);
}
