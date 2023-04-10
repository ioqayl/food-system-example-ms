package org.food.truck.ordering.rest.exception.handler;

import lombok.Builder;

@Builder
public record ErrorDTO(String code, String message) {
}
