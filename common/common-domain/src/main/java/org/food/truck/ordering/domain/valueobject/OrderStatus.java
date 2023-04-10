package org.food.truck.ordering.domain.valueobject;

public enum OrderStatus implements ValueObject {
    PENDING, PAID, APPROVED, CANCELLING, CANCELLED
}
