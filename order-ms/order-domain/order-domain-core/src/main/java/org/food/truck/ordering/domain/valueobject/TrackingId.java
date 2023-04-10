package org.food.truck.ordering.domain.valueobject;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> implements ValueObject {
    public TrackingId(UUID value) {
        super(value);
    }

    public static TrackingId valueOf(UUID uuid) {
        return new TrackingId(uuid);
    }
}
