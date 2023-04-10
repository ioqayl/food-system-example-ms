package org.food.truck.ordering.domain.event;

public interface DomainEventPublisher<T extends DomainEvent<?>> {
    void publish(T domainEvent);
}