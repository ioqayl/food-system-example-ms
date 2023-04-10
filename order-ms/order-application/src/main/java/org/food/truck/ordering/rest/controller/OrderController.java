package org.food.truck.ordering.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.dto.create.CreateOrderRequest;
import org.food.truck.ordering.domain.dto.create.CreateOrderResponse;
import org.food.truck.ordering.domain.dto.track.TrackOrderRequest;
import org.food.truck.ordering.domain.dto.track.TrackOrderResponse;
import org.food.truck.ordering.domain.ports.input.service.OrderApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {
    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        log.info("Creating order for customer: {} at restaurant: {}", createOrderRequest.customerId(), createOrderRequest.restaurantId());
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderRequest);
        log.info("Order created with tracking id: {}", createOrderResponse.trackingId());
        return ResponseEntity.ok(createOrderResponse);
    }

    public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable UUID trackingId) {
        TrackOrderResponse trackOrderResponse = orderApplicationService.trackOrder(TrackOrderRequest.builder().trackingId(trackingId).build());
        log.info("Returning order with tracking id: {}", trackingId);
        return ResponseEntity.ok(trackOrderResponse);
    }
}
