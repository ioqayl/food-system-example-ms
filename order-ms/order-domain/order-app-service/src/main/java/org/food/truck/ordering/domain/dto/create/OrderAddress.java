package org.food.truck.ordering.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderAddress(@NotNull @Max(value = 50) String street,
                           @NotNull String houseNumber,
                           @NotNull String postalCode,
                           @NotNull @Max(value = 50) String city) {
}
