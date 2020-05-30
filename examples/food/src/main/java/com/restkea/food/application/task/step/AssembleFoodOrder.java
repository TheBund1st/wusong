package com.restkea.food.application.task.step;

import com.restkea.food.application.command.PlaceFoodOrderCommand;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.FoodOrderFactory;
import com.restkea.food.domain.order.item.FoodItem;
import com.restkea.food.domain.order.item.FoodItemPrice;
import com.restkea.food.domain.order.item.FoodOrderLineItem;
import com.restkea.food.domain.pricing.FoodPrice;
import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import org.thebund1st.tfb.task.step.When;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
public class AssembleFoodOrder implements When<PlaceFoodOrderContext, FoodOrder> {
    private final FoodOrderFactory orderFactory;

    @Override
    public FoodOrder execute(PlaceFoodOrderContext context) {
        PlaceFoodOrderCommand command = context.getCommand();
        FoodPrice foodPrice = context.getFoodPriceSnapshot();
        List<FoodItem> items = command.getItems();
        FoodOrder order = orderFactory.make(command.getWhen());
        order.setCustomerId(command.getCustomerId());
        order.setStoreId(command.getStoreId());
        order.setLineItems(items
                .stream().map(item -> toLine(item, foodPrice.getItemPrices().get(item))).collect(toList()));
        order.getLineItems().forEach(lineItem -> assignItemPrice(lineItem, foodPrice));
        order.setTotalAmount(foodPrice.getSubtotal());
        return order;
    }

    private FoodOrderLineItem toLine(FoodItem item, FoodItemPrice itemPrice) {
        FoodOrderLineItem lineItem = new FoodOrderLineItem();
        lineItem.setItem(item);
        lineItem.setPrice(itemPrice);
        return lineItem;
    }

    private void assignItemPrice(FoodOrderLineItem lineItem, FoodPrice foodPrice) {
        // assuming every item can get its price
        lineItem.setPrice(foodPrice.getItemPrices().get(lineItem.getItem()));
    }
}
