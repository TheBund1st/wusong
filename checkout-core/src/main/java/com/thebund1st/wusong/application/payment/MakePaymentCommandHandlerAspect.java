package com.thebund1st.wusong.application.payment;

import com.thebund1st.wusong.application.MakePaymentCommandHandler;
import lombok.Setter;

public class MakePaymentCommandHandlerAspect<Cmd, P>
        implements MakePaymentCommandHandler<Cmd, P> {

    @Setter
    private MakePaymentCommandHandler<Cmd, P> target;

    @Setter
    private MakePaymentThen<Cmd, P> then;

    @Override
    public P handle(Cmd command) {
        P payment = target.handle(command);
        then.handle(command, payment);
        return payment;
    }
}
