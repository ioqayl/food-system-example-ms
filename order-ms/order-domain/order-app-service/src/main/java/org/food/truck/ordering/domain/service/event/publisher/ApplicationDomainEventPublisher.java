package org.food.truck.ordering.domain.service.event.publisher;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.event.DomainEventPublisher;
import org.food.truck.ordering.domain.event.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher
        implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        applicationEventPublisher.publishEvent(domainEvent);
    }
}
