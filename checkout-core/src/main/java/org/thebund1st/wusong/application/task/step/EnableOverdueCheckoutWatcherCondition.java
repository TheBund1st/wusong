package org.thebund1st.wusong.application.task.step;

import org.thebund1st.tfb.task.step.ThenCondition;

public interface EnableOverdueCheckoutWatcherCondition<C, O> extends ThenCondition<C, O> {
    @Override
    default boolean satisfiedBy(O returning, C context) {
        return true;
    }
}
