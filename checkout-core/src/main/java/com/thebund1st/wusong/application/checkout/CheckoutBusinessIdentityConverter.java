package com.thebund1st.wusong.application.checkout;

public interface CheckoutBusinessIdentityConverter<Cmd, BID> {
    BID from(Cmd cmd);
}
