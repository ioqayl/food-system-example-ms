package org.food.truck.ordering.domain.dto.message;

import lombok.Builder;
import org.food.truck.ordering.domain.valueobject.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
public record PaymentResponse(String id,
                              String sagaId,
                              String orderId,
                              String paymentId,
                              String customerId,
                              BigDecimal price,
                              Instant createdAt,
                              PaymentStatus paymentStatus,
                              List<String> failureMessages) {
}
