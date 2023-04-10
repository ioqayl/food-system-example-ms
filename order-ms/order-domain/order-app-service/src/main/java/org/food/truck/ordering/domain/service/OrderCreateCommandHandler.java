package org.food.truck.ordering.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.dto.create.CreateOrderRequest;
import org.food.truck.ordering.domain.dto.create.CreateOrderResponse;
import org.food.truck.ordering.domain.event.OrderCreatedEvent;
import org.food.truck.ordering.domain.mapper.OrderDataMapper;
import org.food.truck.ordering.domain.ports.output.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final OrderCreateHelper helper;
    private final OrderDataMapper dataMapper;
    private final OrderCreatedPaymentRequestMessagePublisher messagePublisher;

    public OrderCreateCommandHandler(OrderCreateHelper helper,
                                     OrderDataMapper dataMapper,
                                     OrderCreatedPaymentRequestMessagePublisher messagePublisher) {
        this.helper = helper;
        this.dataMapper = dataMapper;
        this.messagePublisher = messagePublisher;
    }

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        OrderCreatedEvent orderCreatedEvent = helper.persistOrder(createOrderRequest);
        CreateOrderResponse createOrderResponse = dataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        messagePublisher.publish(orderCreatedEvent);
        return createOrderResponse;
    }
}
