package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

public class WrapperAccessor {
    public static String getCategoryDetail(CategoryDetail categoryDetail) {
        return categoryDetail.getValue();
    }

    public static int getQuantity(Quantity quantity) {
        return quantity.getValue();
    }
}
