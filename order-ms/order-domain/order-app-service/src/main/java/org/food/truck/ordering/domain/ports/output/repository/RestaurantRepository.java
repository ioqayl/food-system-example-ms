package org.food.truck.ordering.domain.ports.output.repository;

import org.food.truck.ordering.domain.entity.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository {
    Optional<Restaurant> findById(UUID restaurantId);
}