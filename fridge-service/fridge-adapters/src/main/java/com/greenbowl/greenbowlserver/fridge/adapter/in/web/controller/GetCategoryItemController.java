package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.GetCategoryItemResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GetCategoryItemController {
    private final GetCategoryItemUseCase getCategoryItemUseCase;

    private static final String GET_CATEGORY_ITEM = "카테고리 리스트 조회";
    private static final String GET_CATEGORY_ITEM_DESCRIPTION =
            "로그인 후 카테고리 리스트를 조회할 수 있습니다.";

    @ApiOperation(value = GET_CATEGORY_ITEM, notes = GET_CATEGORY_ITEM_DESCRIPTION)
    @GetMapping("/category-items")
    public ResponseEntity<List<GetCategoryItemResponse>> getCategoryItems() {
        String userId = "1";

        List<CategoryItem> responses = getCategoryItemUseCase.getCategoryItems(Long.parseLong(userId));
        List<GetCategoryItemResponse> result = responses.stream()
                .map(GetCategoryItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
