package com.thebund1st.wusong.application.domain.order;

public interface OrderRepository<OID, O> {
    void save(O order);

    O shouldFindBy(OID orderId);
}
