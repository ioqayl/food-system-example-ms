package org.food.truck.ordering.domain.ports.input.service;

import jakarta.validation.Valid;
import org.food.truck.ordering.domain.dto.create.CreateOrderRequest;
import org.food.truck.ordering.domain.dto.create.CreateOrderResponse;
import org.food.truck.ordering.domain.dto.track.TrackOrderRequest;
import org.food.truck.ordering.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderRequest createOrderRequest);

    TrackOrderResponse trackOrder(@Valid TrackOrderRequest trackOrderRequest);
}
