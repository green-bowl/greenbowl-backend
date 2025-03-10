package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper.FridgeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.CreateCategoryItemRequest;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.CreateCategoryItemResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.CreateCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CreateCategoryItemController {
    private final CreateCategoryItemUseCase createCategoryItemUseCase;

    private static final String CREATE_CATEGORY_ITEM = "카테고리 리스트 추가";
    private static final String CREATE_CATEGORY_ITEM_DESCRIPTION = "카테고리 순번(sequence) 과 카테고리 리스트(categoryDetail)을 입력하여 카테고리 리스트를 추가할 수 있습니다.";
    private static final String CREATE_CATEGORY_ITEM_FORM = "카테고리 리스트 추가 양식";

    @ApiOperation(value = CREATE_CATEGORY_ITEM, notes = CREATE_CATEGORY_ITEM_DESCRIPTION)
    @PostMapping("/category-items")
    public ResponseEntity<CreateCategoryItemResponse> createCategoryItem(
            @RequestBody @ApiParam(value = CREATE_CATEGORY_ITEM_FORM) @Valid CreateCategoryItemRequest createCategoryItemRequest) {
        String userId = "1";
        CreateCategoryItemCommand command = FridgeRequestToCommandMapper.mapToCommand(createCategoryItemRequest);

        CategoryItem result = createCategoryItemUseCase.createCategoryItem(Long.parseLong(userId), command);

        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCategoryItemResponse.from(result));
    }
}
