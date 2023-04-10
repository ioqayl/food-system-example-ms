package org.food.truck.ordering.domain.valueobject;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> implements ValueObject {
    public CustomerId(UUID value) {
        super(value);
    }
}
