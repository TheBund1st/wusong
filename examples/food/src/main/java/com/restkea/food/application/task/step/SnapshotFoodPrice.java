package com.restkea.food.application.task.step;

import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import com.restkea.food.domain.order.item.FoodItem;
import com.restkea.food.domain.pricing.FoodPriceCalculator;
import lombok.RequiredArgsConstructor;
import org.thebund1st.wusong.application.task.step.given.TakePriceSnapshot;

import java.util.List;


@RequiredArgsConstructor
public class SnapshotFoodPrice implements TakePriceSnapshot<PlaceFoodOrderContext> {
    private final FoodPriceCalculator foodPriceCalculator;

    @Override
    public void execute(PlaceFoodOrderContext context) {
        List<FoodItem> items = context.getCommand().getItems();
        context.setFoodPriceSnapshot(foodPriceCalculator.calculateWith(items));
    }
}
