package org.food.truck.ordering.domain.mapper;

import org.food.truck.ordering.domain.dto.create.CreateOrderRequest;
import org.food.truck.ordering.domain.dto.create.CreateOrderResponse;
import org.food.truck.ordering.domain.dto.create.OrderAddress;
import org.food.truck.ordering.domain.dto.track.TrackOrderResponse;
import org.food.truck.ordering.domain.entity.Order;
import org.food.truck.ordering.domain.entity.OrderItem;
import org.food.truck.ordering.domain.entity.Product;
import org.food.truck.ordering.domain.entity.Restaurant;
import org.food.truck.ordering.domain.valueobject.*;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public Restaurant createOrderRequestToRestaurant(CreateOrderRequest createOrderRequest) {
        return Restaurant.builder()
                .id(RestaurantId.valueOf(createOrderRequest.restaurantId()))
                .products(createOrderRequest.items()
                            .stream()
                            .map(orderItem ->
                                    new Product(ProductId.valueOf(orderItem.productId())))
                            .collect(Collectors.toList()))
                .build();
    }

    public Order createOrderRequestToOrder(CreateOrderRequest createOrderRequest) {
        return Order.builder()
                .customerId(new CustomerId(createOrderRequest.customerId()))
                .restaurantId(new RestaurantId(createOrderRequest.restaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderRequest.address()))
                .price(Money.valueOf(createOrderRequest.price()))
                .items(createOrderRequest.items().stream()
                        .map(this::createOrderRequestOrderItemToOrderItem)
                        .collect(Collectors.toList()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .trackingId(order.getTrackingId().getValue().toString())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order) {
        return TrackOrderResponse.builder()
                .trackingId(order.getTrackingId().getValue().toString())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return StreetAddress.builder()
                .id(UUID.randomUUID())
                .city(orderAddress.city())
                .street(orderAddress.street())
                .houseNumber(orderAddress.houseNumber())
                .postalCode(orderAddress.postalCode())
                .build();
    }

    public OrderItem createOrderRequestOrderItemToOrderItem(org.food.truck.ordering.domain.dto.create.OrderItem orderItem) {
        return OrderItem.builder()
                .product(new Product(ProductId.valueOf(orderItem.productId())))
                .price(Money.valueOf(orderItem.price()))
                .quantity(orderItem.quantity())
                .subTotal(Money.valueOf(orderItem.subTotal()))
                .build();
    }
}
