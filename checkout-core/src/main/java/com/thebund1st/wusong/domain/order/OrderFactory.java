package com.thebund1st.wusong.domain.order;

public interface OrderFactory<BID, O> {
    O make(BID businessIdentity);
}
