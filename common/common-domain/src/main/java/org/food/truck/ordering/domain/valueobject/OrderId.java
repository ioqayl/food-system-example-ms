package org.food.truck.ordering.domain.valueobject;

import java.util.UUID;

public class OrderId extends BaseId<UUID> implements ValueObject {
    public OrderId(UUID value) {
        super(value);
    }

    public static OrderId valueOf(UUID uuid) {
        return new OrderId(uuid);
    }
}
