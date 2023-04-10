package org.food.truck.ordering.order.database.order.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntityId {
    private Long id;
    private OrderEntity order;

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (Objects.nonNull(obj) && obj.getClass().equals(getClass()))
            isEqual = (this == obj) ||
                    (id.equals(((OrderItemEntityId) obj).id) && order.equals(order));

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }
}