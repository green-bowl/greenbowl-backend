package com.greenbowl.greenbowlserver.fridge.domain.wrapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;

@Getter
@RequiredArgsConstructor
public enum Category {
    VEGETABLES(1),     // 채소
    FRUITS(2),         // 과일
    MEAT(3),           // 육류
    SEAFOOD(4),        // 수산물
    CONDIMENTS(5),     // 조미료
    DAIRY_PROCESSED(6), // 가공/유제품
    NUTS_GRAINS(7),    // 견과/곡물
    OTHERS(8);          // 기타


    private final int sequence;

    int getValue() {
        return sequence;
    }

    public static Category from(int sequence) {
        for (Category category : Category.values()) {
            if (category.sequence == sequence) {
                return category;
            }
        }
        return null;
    }

    public static class CategoryConverter implements AttributeConverter<Category, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Category category) {
            return category == null ? null : category.sequence;
        }

        @Override
        public Category convertToEntityAttribute(Integer integer) {
            return integer == null ? null : Category.from(integer);
        }
    }
}
