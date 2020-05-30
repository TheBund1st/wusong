package org.thebund1st.tfb.task.step;

public class GivenDummy<C> implements Given<C> {

    @Override
    public void execute(C context) {

    }

    public static <C> GivenDummy<C> dummyGiven() {
        return new GivenDummy<>();
    }
}
