package com.thebund1st.wusong.domain.payment;

import java.time.LocalDateTime;

public interface PaymentFactory<BID, P> {
    P make(BID businessIdentity, LocalDateTime createdAt);
}
