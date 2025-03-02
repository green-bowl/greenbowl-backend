package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDetail)) return false;
        CategoryDetail that = (CategoryDetail) o;
        return Objects.equals(categoryDetail, that.categoryDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryDetail);
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
