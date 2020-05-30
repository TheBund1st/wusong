package org.thebund1st.tfb.task.step;

import org.thebund1st.tfb.task.Task;
import lombok.Setter;

@Setter
public class GherkinTask<C, R> implements Task<C, R> {
    private Given<C> given;
    private When<C, R> when;
    private Then<C, R> then;

    @Override
    public R execute(C context) {
        given.execute(context);
        R returning = when.execute(context);
        then.execute(returning, context);
        return returning;
    }

}
