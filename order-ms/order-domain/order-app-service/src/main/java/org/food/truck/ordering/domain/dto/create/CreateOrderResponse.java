package org.food.truck.ordering.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.food.truck.ordering.domain.valueobject.OrderStatus;

import java.util.UUID;

@Builder
public record CreateOrderResponse(@NotNull String trackingId,
                                  @NotNull OrderStatus orderStatus,
                                  @NotNull String message) {
}
