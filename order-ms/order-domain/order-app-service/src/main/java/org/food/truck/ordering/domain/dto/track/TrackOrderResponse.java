package org.food.truck.ordering.domain.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.food.truck.ordering.domain.valueobject.OrderStatus;

import java.util.List;

@Builder
public record TrackOrderResponse(@NotNull String trackingId,
                                 @NotNull OrderStatus orderStatus,
                                 List<String> failureMessages) {
}