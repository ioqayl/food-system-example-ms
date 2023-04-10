package org.food.truck.ordering.domain.valueobject;

import java.util.UUID;

public class OrderItemId extends BaseId<Integer> implements ValueObject {
    public OrderItemId(Integer value) {
        super(value);
    }

    public static OrderItemId valueOf(Integer id) {
        return new OrderItemId(id);
    }
}
