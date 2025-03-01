package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem;

import com.greenbowl.greenbowlserver.common.adapter.out.persistence.audit.BaseGeneralEntity;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.CategoryDetail;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "default_category_item")
@Table(name = "default_category_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DefaultCategoryItemJpaEntity extends BaseGeneralEntity {
    @Convert(converter = CategoryDetail.CategoryDetailConverter.class)
    @Column
    private CategoryDetail categoryDetail;

    @Convert(converter = Category.CategoryConverter.class)
    private Category category;

    @Builder
    public DefaultCategoryItemJpaEntity(CategoryDetail categoryDetail, Category category){
        this.categoryDetail = categoryDetail;
        this.category = category;
    }

    public void deleteDefaultCategoryItem(){
        super.deleteEntity();
    }

    public static DefaultCategoryItemJpaEntity from(DefaultCategoryItem defaultCategoryItem){
        CategoryDetail categoryDetail = defaultCategoryItem.getCategoryDetail();
        Category category = defaultCategoryItem.getCategory();

        return DefaultCategoryItemJpaEntity.builder()
                .categoryDetail(categoryDetail)
                .category(category)
                .build();
    }
}
