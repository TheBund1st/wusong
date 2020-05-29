package com.thebund1st.wusong.domain.order;

import java.time.LocalDateTime;

public interface OrderFactory<BID, O> {
    O make(BID businessIdentity, LocalDateTime createdAt);
}
