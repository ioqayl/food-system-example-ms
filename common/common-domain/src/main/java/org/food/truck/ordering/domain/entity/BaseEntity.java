package org.food.truck.ordering.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (Objects.nonNull(obj) && obj.getClass().equals(getClass()))
            isEqual = (this == obj) || id.equals(((BaseEntity<ID>) obj).id);

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
