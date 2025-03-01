package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetDefaultCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetDefaultCategoryItemPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.wrapper.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDefaultCategoryItemService implements GetDefaultCategoryItemUseCase {
    private final GetDefaultCategoryItemPort getDefaultCategoryItemPort;

    @Override
    public List<DefaultCategoryItem> getDefaultCategoryItemBySequence(int sequence) {
        return getDefaultCategoryItemPort.getCategoryItemBySequence(Category.from(sequence));
    }
}
