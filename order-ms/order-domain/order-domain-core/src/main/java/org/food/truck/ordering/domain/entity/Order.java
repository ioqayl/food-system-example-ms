package org.food.truck.ordering.domain.entity;

import org.food.truck.ordering.domain.exception.OrderDomainException;
import org.food.truck.ordering.domain.valueobject.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.function.Predicate.not;

public class Order extends AggregateRoot<OrderId>{
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        this.setId(builder.id);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(TrackingId trackingId) {
        this.trackingId = trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public void setFailureMessages(List<String> failureMessages) {
        this.failureMessages = failureMessages;
    }

    public void initializeOrder() {
        AtomicInteger count = new AtomicInteger(0);
        setId(OrderId.valueOf(UUID.randomUUID()));
        trackingId = TrackingId.valueOf(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        items.forEach(item -> item.setId(OrderItemId.valueOf(count.getAndIncrement())));
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null)
            throw new OrderDomainException("Order is not correct state for initialization");
    }

    private void validateTotalPrice() {
        if (price == null || price.isLessThanOrEqualTo(Money.ZERO))
            throw new OrderDomainException("Total price must be greater than zero");
    }

    private void validateItemsPrice() {
        Money itemsTotalMoney = items.stream()
                .peek(OrderItem::validateItemPrice)
                .map(OrderItem::getSubTotal)
                .reduce(Money.ZERO, Money::plus);

        if (!itemsTotalMoney.isEqualTo(price))
            throw new OrderDomainException("Total price " + price.getAmount()
                    + " is not equal to items total amount " + itemsTotalMoney.getAmount());
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING)
            throw new OrderDomainException("Order is not in correct state for pay operation");

        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for approve operation");

        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID)
            throw new OrderDomainException("Order is not in correct state for init cancelling operation");

        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (!EnumSet.of(OrderStatus.CANCELLING, OrderStatus.PENDING).contains(orderStatus))
            throw new OrderDomainException("Order is not in correct state for cancel operation");

        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages == null)
            this.failureMessages = new ArrayList<>();

        if(failureMessages != null)
            this.failureMessages.addAll(failureMessages.stream().filter(not(String::isEmpty)).toList());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages = new ArrayList<>();

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
