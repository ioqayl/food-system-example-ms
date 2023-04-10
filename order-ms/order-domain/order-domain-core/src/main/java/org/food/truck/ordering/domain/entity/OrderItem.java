package org.food.truck.ordering.domain.entity;

import org.food.truck.ordering.domain.exception.OrderDomainException;
import org.food.truck.ordering.domain.valueobject.Money;
import org.food.truck.ordering.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    public OrderItem(OrderItemId orderItemId, Product product, int quantity, Money price, Money subTotal) {
        this.setId(orderItemId);
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

    private OrderItem(Builder builder) {
        this.setId(builder.id);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isValidPrice() {
        return price.isGreaterThan(Money.ZERO) && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    public void validateItemPrice() {
        if (!isValidPrice())
            throw new OrderDomainException("Order item price: " + getPrice().getAmount()
                    + " is not equal to Order items total: " + getProduct().getId().getValue());
    }

    public static final class Builder {
        private OrderItemId id;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
