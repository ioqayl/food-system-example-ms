package org.food.truck.ordering.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.dto.create.CreateOrderRequest;
import org.food.truck.ordering.domain.dto.create.CreateOrderResponse;
import org.food.truck.ordering.domain.dto.track.TrackOrderRequest;
import org.food.truck.ordering.domain.dto.track.TrackOrderResponse;
import org.food.truck.ordering.domain.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                       OrderTrackCommandHandler orderTrackCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackCommandHandler = orderTrackCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        return orderCreateCommandHandler.createOrder(createOrderRequest);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderRequest trackOrderRequest) {
        return orderTrackCommandHandler.trackOrder(trackOrderRequest);
    }
}