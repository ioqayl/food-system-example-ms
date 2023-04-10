package org.food.truck.ordering.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.food.truck.ordering.domain.dto.create.CreateOrderRequest;
import org.food.truck.ordering.domain.dto.create.CreateOrderResponse;
import org.food.truck.ordering.domain.entity.Customer;
import org.food.truck.ordering.domain.entity.Order;
import org.food.truck.ordering.domain.entity.Restaurant;
import org.food.truck.ordering.domain.event.OrderCreatedEvent;
import org.food.truck.ordering.domain.exception.OrderDomainException;
import org.food.truck.ordering.domain.mapper.OrderDataMapper;
import org.food.truck.ordering.domain.ports.output.repository.CustomerRepository;
import org.food.truck.ordering.domain.ports.output.repository.OrderRepository;
import org.food.truck.ordering.domain.ports.output.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository,
                             CustomerRepository customerRepository,
                             RestaurantRepository restaurantRepository,
                             OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderRequest createOrderRequest) {
        checkCustomer(createOrderRequest.customerId());
        Restaurant restaurant = checkRestaurant(createOrderRequest);
        Order order = orderDataMapper.createOrderRequestToOrder(createOrderRequest);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }

    private Restaurant checkRestaurant(CreateOrderRequest createOrderRequest) {
        Restaurant restaurant = orderDataMapper.createOrderRequestToRestaurant(createOrderRequest);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(createOrderRequest.restaurantId());

        if (restaurantOptional.isEmpty()) {
            log.error("Could not find restaurant with restaurant id: {}", createOrderRequest.restaurantId());
            throw new OrderDomainException("Could not find restaurant with restaurant id: " + createOrderRequest.restaurantId());
        }

        return restaurantOptional.get();
    }

    private Customer checkCustomer(UUID customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            log.error("Could not find customer with customer id: {}", customerId);
            throw new OrderDomainException("Could not find customer with customer id: " + customerId);
        }

        return customerOptional.get();
    }

    private Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        if (savedOrder == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }

        log.info("Order is saved with id: {}", savedOrder.getId().getValue());

        return savedOrder;
    }
}
