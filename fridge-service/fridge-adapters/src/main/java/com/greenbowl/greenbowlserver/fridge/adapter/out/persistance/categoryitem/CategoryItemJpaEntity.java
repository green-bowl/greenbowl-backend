package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.categoryitem;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.CategoryDetail;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "category_item")
@Table(name = "category_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryItemJpaEntity extends BaseGeneralEntity {

    @Column(nullable = false)
    private Long userId;

    @Convert(converter = CategoryDetail.CategoryDetailConverter.class)
    @Column(nullable = false)
    private CategoryDetail categoryDetail;

    @Convert(converter = Category.CategoryConverter.class)
    @Column(nullable = false)
    private Category category;

    @Builder
    public CategoryItemJpaEntity(CategoryDetail categoryDetail, Category category, Long userId) {
        this.userId = userId;
        this.categoryDetail = categoryDetail;
        this.category = category;
    }

    public void deleteCategoryItem(){
        super.deleteEntity();
    }

    public static CategoryItemJpaEntity from(CategoryItem categoryItem) {
        CategoryDetail categoryDetail = categoryItem.getCategoryDetail();
        Category category = categoryItem.getCategory();

        return CategoryItemJpaEntity.builder()
                .userId(categoryItem.getUserId())
                .category(category)
                .categoryDetail(categoryDetail)
                .build();
    }
}
