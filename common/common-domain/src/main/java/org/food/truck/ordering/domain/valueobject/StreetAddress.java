package org.food.truck.ordering.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class StreetAddress implements ValueObject {
    private final UUID id;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;

    public StreetAddress(UUID id, String street, String houseNumber, String postalCode, String city) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    private StreetAddress(Builder builder) {
        id = builder.id;
        street = builder.street;
        houseNumber = builder.houseNumber;
        postalCode = builder.postalCode;
        city = builder.city;
    }

    public UUID getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (Objects.nonNull(obj) && obj.getClass().equals(getClass())) {
            StreetAddress streetAddress = (StreetAddress) obj;
            isEqual = (this == streetAddress) || (street.equals(streetAddress.street)
                    && houseNumber.equals(streetAddress.houseNumber)
                    && postalCode.equals(streetAddress.postalCode)
                    && city.equals(streetAddress.city));
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, postalCode, city);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String street;
        private String houseNumber;
        private String postalCode;
        private String city;

        private Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder houseNumber(String val) {
            houseNumber = val;
            return this;
        }

        public Builder postalCode(String val) {
            postalCode = val;
            return this;
        }

        public Builder city(String val) {
            city = val;
            return this;
        }

        public StreetAddress build() {
            return new StreetAddress(this);
        }
    }
}
