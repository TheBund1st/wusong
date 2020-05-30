package org.thebund1st.tfb.task.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ThenChain<C, R> implements Then<C, R> {

    private final List<Then<C, R>> chain;

    @Override
    public void execute(R returning, C context) {
        chain.forEach(then -> then.execute(returning, context));
    }

}
