package org.food.truck.ordering.domain.service.message.listener;

import org.food.truck.ordering.domain.dto.message.RestaurantApprovalResponse;
import org.food.truck.ordering.domain.ports.input.message.listener.RestaurantApprovalMessageResponseListener;

public class RestaurantApprovalMessageResponseListenerImpl implements RestaurantApprovalMessageResponseListener {
    @Override
    public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

    }
}
