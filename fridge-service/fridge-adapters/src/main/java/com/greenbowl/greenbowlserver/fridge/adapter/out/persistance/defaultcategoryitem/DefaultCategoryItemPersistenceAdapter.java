package com.greenbowl.greenbowlserver.fridge.adapter.out.persistance.defaultcategoryitem;

import com.greenbowl.greenbowlserver.fridge.adapter.out.mapper.FridgeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetDefaultCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultCategoryItemPersistenceAdapter implements GetDefaultCategoryItemPort {
    private final DefaultCategoryItemJpaRepository defaultCategoryItemJpaRepository;

    @Override
    public List<DefaultCategoryItem> getCategoryItemBySequence(Category category) {
        List<DefaultCategoryItemJpaEntity> defaultCategoryItemJpaEntities
                = defaultCategoryItemJpaRepository.findAllByCategoryAndDeleteYnFalse(category);

        return defaultCategoryItemJpaEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DefaultCategoryItem> getAllCategoryItems() {
        List<DefaultCategoryItemJpaEntity> defaultCategoryItemJpaEntities
                = defaultCategoryItemJpaRepository.findAllByDeleteYnFalse();

        return defaultCategoryItemJpaEntities.stream()
                .map(FridgeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
