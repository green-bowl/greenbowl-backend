package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class Quantity {
    private int quantity;

    public Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity of(int quantity) {
        return new Quantity(quantity);
    }

    int getValue() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity that = (Quantity) o;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    public static class QuantityConverter implements AttributeConverter<Quantity, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Quantity quantity) {
            return quantity == null ? null : quantity.quantity;
        }

        @Override
        public Quantity convertToEntityAttribute(Integer integer) {
            return integer == null ? null : new Quantity(integer);
        }
    }
}
