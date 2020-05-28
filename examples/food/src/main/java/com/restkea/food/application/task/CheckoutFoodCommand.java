package com.restkea.food.application.task;

import com.restkea.food.application.domain.order.item.FoodItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CheckoutFoodCommand {
    private String customerId;
    private String storeId;
    private List<FoodItem> items = new ArrayList<>();
    private LocalDateTime when;
}
