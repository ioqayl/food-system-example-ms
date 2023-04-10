package org.food.truck.ordering.domain.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TrackOrderRequest(@NotNull UUID trackingId) {
}
