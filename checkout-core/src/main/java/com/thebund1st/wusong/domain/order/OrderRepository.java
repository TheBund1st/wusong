package com.thebund1st.wusong.domain.order;

public interface OrderRepository<OID, O> {
    void save(O order);

    O shouldFindBy(OID orderId);
}
