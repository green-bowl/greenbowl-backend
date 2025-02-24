package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public class CategoryDetail {
    private final String categoryDetail;

    public CategoryDetail(String categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    public static CategoryDetail of(String name) {
        return new CategoryDetail(name);
    }

    String getValue() {
        return categoryDetail;
    }

    @Converter
    public static class CategoryDetailConverter implements AttributeConverter<CategoryDetail, String> {
        @Override
        public String convertToDatabaseColumn(CategoryDetail categoryDetail) {
            return categoryDetail == null ? null : categoryDetail.categoryDetail;
        }

        @Override
        public CategoryDetail convertToEntityAttribute(String categoryDetail) {
            return categoryDetail == null ? null : new CategoryDetail(categoryDetail);
        }
    }
}
