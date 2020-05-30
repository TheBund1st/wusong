package org.thebund1st.tfb.task.step;

public interface WhenReturnVoid<C> extends When<C, Void> {

    @Override
    default Void execute(C context) {
        doExecute(context);
        return null;
    }

    void doExecute(C context);
}
