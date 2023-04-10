package org.food.truck.ordering.order.database.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_address")
@Entity
public class OrderAddressEntity {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    private String street;
    private String postalCode;
    private String city;

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (Objects.nonNull(obj) && obj.getClass().equals(getClass()))
            isEqual = (this == obj) || id.equals(((OrderAddressEntity) obj).id);

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}