package org.food.truck.ordering.domain.valueobject;

import java.util.UUID;

public class ProductId extends BaseId<UUID> implements ValueObject {
    public ProductId(UUID value) {
        super(value);
    }

    public static ProductId valueOf(UUID uuid) {
        return new ProductId(uuid);
    }
}
