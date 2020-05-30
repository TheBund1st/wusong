package org.thebund1st.tfb.task.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ThenConditional<C, R> implements Then<C, R> {

    private final ThenCondition<C, R> condition;
    private final Then<C, R> then;

    @Override
    public void execute(R returning, C context) {
        if (condition.satisfiedBy(returning, context)) {
            log.info("[Step:{}] should be enabled for [{}]", then, returning);
            then.execute(returning, context);
        } else {
            log.info("[Step:{}] is skipped for [{}]", then, returning);
        }
    }

}
