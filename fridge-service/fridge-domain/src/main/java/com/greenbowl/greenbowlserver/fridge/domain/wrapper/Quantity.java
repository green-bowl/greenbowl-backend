package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

import javax.persistence.AttributeConverter;

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
