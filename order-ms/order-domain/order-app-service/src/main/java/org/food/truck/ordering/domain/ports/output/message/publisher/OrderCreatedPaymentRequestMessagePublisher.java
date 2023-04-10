package org.food.truck.ordering.domain.ports.output.message.publisher;

import org.food.truck.ordering.domain.event.DomainEventPublisher;
import org.food.truck.ordering.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
