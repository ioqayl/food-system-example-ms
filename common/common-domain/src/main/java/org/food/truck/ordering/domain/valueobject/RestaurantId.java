package org.food.truck.ordering.domain.valueobject;

import java.util.UUID;

public class RestaurantId extends BaseId<UUID> implements ValueObject {
    public RestaurantId(UUID value) {
        super(value);
    }

    public static RestaurantId valueOf(UUID uuid) {
        return new RestaurantId(uuid);
    }
}
