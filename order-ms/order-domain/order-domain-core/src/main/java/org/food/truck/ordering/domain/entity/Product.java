package org.food.truck.ordering.domain.entity;

import org.food.truck.ordering.domain.valueobject.Money;
import org.food.truck.ordering.domain.valueobject.ProductId;

import java.util.UUID;

public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(ProductId productId) {
        this.setId(productId);
    }

    public Product(ProductId productId, String name, Money price) {
        this.setId(productId);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
