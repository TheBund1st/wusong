package com.restkea.food.application.domain.order;

import com.restkea.food.application.domain.order.item.FoodOrderLineItem;
import com.restkea.food.application.domain.order.overdue.Overdue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of = "orderId")
@ToString(of = "orderId")
public class FoodOrder {
    private String orderId;
    private String customerId;
    private String storeId;
    private List<FoodOrderLineItem> lineItems = new ArrayList<>();
    private double totalAmount;
    private double paidAmount;
    private Overdue overdue = Overdue.ENABLED;
    private LocalDateTime createdAt;
    private LocalDateTime overdueClosedAt;
    private LocalDateTime canceledAt;
    private FoodOrderStatus status = FoodOrderStatus.NEW;

    public void getPaidWith(double amount) {
        this.paidAmount += amount;
    }

    private boolean itShouldCloseOverdue() {
        return overdue == Overdue.ENABLED && totalAmount - paidAmount == 0;
    }

    public void closeOverdue(LocalDateTime when) {
        this.overdue = Overdue.CLOSED;
        this.overdueClosedAt = when;
    }

    public void cancel(LocalDateTime when) {
        this.status = FoodOrderStatus.CANCELED;
        this.canceledAt = when;
    }

    public boolean isOverdueWatcherEnabled() {
        return this.overdue == Overdue.ENABLED;
    }

    public boolean isBalanced() {
        return totalAmount == paidAmount;
    }
}
