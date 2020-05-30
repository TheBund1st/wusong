package org.thebund1st.tfb.task.step;

public interface When<C, R> {

    R execute(C context);

}
