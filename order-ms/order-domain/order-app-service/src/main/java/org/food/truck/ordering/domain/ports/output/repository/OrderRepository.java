package org.food.truck.ordering.domain.ports.output.repository;

import org.food.truck.ordering.domain.entity.Order;
import org.food.truck.ordering.domain.valueobject.TrackingId;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId);
}