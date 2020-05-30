package org.thebund1st.tfb.task;

public interface Task<C, R> {

    R execute(C context);

}
