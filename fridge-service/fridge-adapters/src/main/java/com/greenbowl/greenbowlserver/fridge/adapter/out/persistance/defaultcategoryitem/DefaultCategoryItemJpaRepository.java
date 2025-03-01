package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem;

import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefaultCategoryItemJpaRepository extends JpaRepository<DefaultCategoryItemJpaEntity, Long> {
    List<DefaultCategoryItemJpaEntity> findAllByCategoryAndDeleteYnFalse(Category category);
    List<DefaultCategoryItemJpaEntity> findAllByDeleteYnFalse();
}
