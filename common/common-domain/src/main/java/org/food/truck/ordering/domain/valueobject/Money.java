package org.food.truck.ordering.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money implements ValueObject {
    private final BigDecimal amount;
    private final Integer scale;
    private final RoundingMode roundingMode;

    public final static Money ZERO = valueOf(BigDecimal.ZERO);

    private Money(BigDecimal amount, Integer scale, RoundingMode roundingMode) {
        this.amount = amount;
        this.scale = scale;
        this.roundingMode = roundingMode;

        amount.setScale(scale, roundingMode);
    }

    public static Money valueOf(BigDecimal amount) {
        return valueOf(amount, 2, RoundingMode.HALF_EVEN);
    }

    public static Money valueOf(BigDecimal amount, Integer scale, RoundingMode roundingMode) {
        return new Money(amount, scale, roundingMode);
    }

    public static Money valueOf(int amount) {
        return valueOf(amount, 2, RoundingMode.HALF_EVEN);
    }

    public static Money valueOf(int amount, Integer scale, RoundingMode roundingMode) {
        return valueOf(BigDecimal.valueOf(amount), scale, roundingMode);
    }

    public static Money valueOf(long amount) {
        return valueOf(amount, 2, RoundingMode.HALF_EVEN);
    }

    public static Money valueOf(long amount, Integer scale, RoundingMode roundingMode) {
        return valueOf(BigDecimal.valueOf(amount), scale, roundingMode);
    }

    public static Money valueOf(float amount) {
        return valueOf(amount, 2, RoundingMode.HALF_EVEN);
    }

    public static Money valueOf(float amount, Integer scale, RoundingMode roundingMode) {
        return valueOf(BigDecimal.valueOf(amount), scale, roundingMode);
    }

    public static Money valueOf(double amount) {
        return valueOf(amount, 2, RoundingMode.HALF_EVEN);
    }

    public static Money valueOf(double amount, Integer scale, RoundingMode roundingMode) {
        return valueOf(BigDecimal.valueOf(amount), scale, roundingMode);
    }

    public boolean isEqualTo(Money money) {
        return Objects.nonNull(amount) && amount.compareTo(money.amount) == 0;
    }

    public boolean isGreaterThan(Money money) {
        return Objects.nonNull(amount) && amount.compareTo(money.amount) > 0;
    }

    public boolean isGreaterThanOrEqualTo(Money money) {
        return isGreaterThan(money) || isEqualTo(money);
    }

    public boolean isLessThan(Money money) {
        return Objects.nonNull(amount) && amount.compareTo(money.amount) < 0;
    }

    public boolean isLessThanOrEqualTo(Money money) {
        return isLessThan(money) || isEqualTo(money);
    }

    public Money plus(Money money) {
        return Money.valueOf(amount.add(money.amount), scale, roundingMode);
    }

    public Money minus(Money money) {
        return Money.valueOf(amount.subtract(money.amount), scale, roundingMode);
    }

    public Money multiply(double multiplier) {
        return Money.valueOf(amount.multiply(BigDecimal.valueOf(multiplier)), scale, roundingMode);
    }

    public Money divide(double divider) {
        return Money.valueOf(amount.divide(BigDecimal.valueOf(divider)), scale, roundingMode);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;

        if (Objects.nonNull(obj) && obj.getClass().equals(getClass()))
            isEqual = (this == obj) || amount.equals(((Money) obj).amount);

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}