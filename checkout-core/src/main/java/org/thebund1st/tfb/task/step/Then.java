package org.thebund1st.tfb.task.step;

public interface Then<C, R> {

    void execute(R returning, C context);

}
