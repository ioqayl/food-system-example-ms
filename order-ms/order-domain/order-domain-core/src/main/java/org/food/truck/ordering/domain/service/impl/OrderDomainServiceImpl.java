package org.food.truck.ordering.domain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.config.ZoneIds;
import org.food.truck.ordering.domain.entity.Order;
import org.food.truck.ordering.domain.entity.Product;
import org.food.truck.ordering.domain.entity.Restaurant;
import org.food.truck.ordering.domain.event.OrderCancelledEvent;
import org.food.truck.ordering.domain.event.OrderCreatedEvent;
import org.food.truck.ordering.domain.event.OrderPaidEvent;
import org.food.truck.ordering.domain.exception.OrderDomainException;
import org.food.truck.ordering.domain.service.OrderDomainService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initiated", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneIds.APP_ZONE));
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive())
            throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() +
                    " is currently active");
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> {
            Product currentProduct = orderItem.getProduct();
            Optional<Product> optionalRestaurantProduct =
                    restaurant.getProducts().stream().filter(currentProduct::equals).findFirst();

            if (optionalRestaurantProduct.isPresent()) {
                Product restaurantProduct = optionalRestaurantProduct.get();
                currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(),
                        restaurantProduct.getPrice());
            }
        });
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneIds.APP_ZONE));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order with id: {}", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneIds.APP_ZONE));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }
}
