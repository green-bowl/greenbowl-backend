package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper.FridgeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.DeleteCategoryItemRequest;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteCategoryItemCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.DeleteCategoryItemUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class DeleteCategoryItemController {
    private final DeleteCategoryItemUseCase deleteCategoryItemUseCase;

    private static final String DELETE_CATEGORY_ITEM = "카테고리 리스트 삭제";
    private static final String DELETE_CATEGORY_ITEM_DESCRIPTION =
            "카테고리ID 를 입력받아 삭제할 수 있습니다.";
    private static final String DELETE_CATEGORY_ITEM_FORM = "카테고리 리스트 삭제 양식";

    @ApiOperation(value = DELETE_CATEGORY_ITEM, notes = DELETE_CATEGORY_ITEM_DESCRIPTION)
    @DeleteMapping("/category-items")
    public ResponseEntity<Void> deleteCategoryItem(
            @RequestBody @Valid @ApiParam(value = DELETE_CATEGORY_ITEM_FORM) DeleteCategoryItemRequest deleteCategoryItemRequest) {
        String userId = "1";

        DeleteCategoryItemCommand deleteCategoryItemCommand
                = FridgeRequestToCommandMapper.mapToCommand(deleteCategoryItemRequest);
        deleteCategoryItemUseCase.deleteCategoryItem(Long.parseLong(userId), deleteCategoryItemCommand);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
