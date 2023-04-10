package org.food.truck.ordering.domain.ports.input.message.listener;

import org.food.truck.ordering.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalMessageResponseListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
