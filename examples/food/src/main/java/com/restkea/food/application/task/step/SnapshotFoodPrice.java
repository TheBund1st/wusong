package com.restkea.food.application.task.step;

import com.restkea.food.domain.order.item.FoodItem;
import com.restkea.food.domain.pricing.FoodPriceCalculator;
import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import org.thebund1st.tfb.task.step.Given;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class SnapshotFoodPrice implements Given<PlaceFoodOrderContext> {
    private final FoodPriceCalculator foodPriceCalculator;

    @Override
    public void execute(PlaceFoodOrderContext context) {
        List<FoodItem> items = context.getCommand().getItems();
        context.setFoodPriceSnapshot(foodPriceCalculator.calculateWith(items));
    }
}
