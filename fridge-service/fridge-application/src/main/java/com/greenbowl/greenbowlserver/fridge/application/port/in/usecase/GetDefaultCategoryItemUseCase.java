package com.greenbowl.greenbowlserver.fridge.application.port.in.usecase;

import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;

import java.util.List;

public interface GetDefaultCategoryItemUseCase {
    List<DefaultCategoryItem> getDefaultCategoryItemBySequence(int sequence);
}
