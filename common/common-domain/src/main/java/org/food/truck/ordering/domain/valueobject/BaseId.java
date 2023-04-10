package org.food.truck.ordering.domain.valueobject;

import lombok.NonNull;

import java.util.Objects;

public abstract class BaseId<T> {
    private final T value;

    public BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (Objects.nonNull(obj) && obj.getClass().equals(getClass()))
            isEqual = (this == obj) || value.equals(((BaseId<T>) obj).value);

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
