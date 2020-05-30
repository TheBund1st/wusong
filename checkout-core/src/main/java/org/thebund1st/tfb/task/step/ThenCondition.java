package org.thebund1st.tfb.task.step;

public interface ThenCondition<C, R> {
    boolean satisfiedBy(R returning, C context);
}
