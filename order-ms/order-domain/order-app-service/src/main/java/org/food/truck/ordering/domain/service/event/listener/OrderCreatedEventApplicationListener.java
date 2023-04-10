package org.food.truck.ordering.domain.service.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.event.OrderCreatedEvent;
import org.food.truck.ordering.domain.ports.output.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class OrderCreatedEventApplicationListener {
    private final OrderCreatedPaymentRequestMessagePublisher messagePublisher;

    public OrderCreatedEventApplicationListener(OrderCreatedPaymentRequestMessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @TransactionalEventListener
    void process(OrderCreatedEvent domainEvent) {
        messagePublisher.publish(domainEvent);
    }
}
