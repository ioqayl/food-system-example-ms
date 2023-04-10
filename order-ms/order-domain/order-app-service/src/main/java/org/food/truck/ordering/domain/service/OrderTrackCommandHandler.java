package org.food.truck.ordering.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.dto.track.TrackOrderRequest;
import org.food.truck.ordering.domain.dto.track.TrackOrderResponse;
import org.food.truck.ordering.domain.entity.Order;
import org.food.truck.ordering.domain.exception.OrderNotFoundException;
import org.food.truck.ordering.domain.mapper.OrderDataMapper;
import org.food.truck.ordering.domain.ports.output.repository.OrderRepository;
import org.food.truck.ordering.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {
    private final OrderDataMapper mapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper mapper, OrderRepository orderRepository) {
        this.mapper = mapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderRequest trackOrderRequest) {
        Optional<Order> orderOptional =
                orderRepository.findByTrackingId(new TrackingId(trackOrderRequest.trackingId()));

        if (orderOptional.isEmpty()) {
            log.error("Could not find order with tracking id: {}", trackOrderRequest.trackingId());
            throw new OrderNotFoundException("Could not find order with tracking id: " + trackOrderRequest.trackingId());
        }

        return mapper.orderToTrackOrderResponse(orderOptional.get());
    }
}
