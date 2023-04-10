package org.food.truck.ordering.domain.dto.message;

import lombok.Builder;
import org.food.truck.ordering.domain.valueobject.OrderApprovalStatus;

import java.time.Instant;

@Builder
public record RestaurantApprovalResponse(String id,
                                         String sagaId,
                                         String orderId,
                                         String paymentId,
                                         String restaurantId,
                                         Instant createdAt,
                                         OrderApprovalStatus orderApprovalStatus) {
}
