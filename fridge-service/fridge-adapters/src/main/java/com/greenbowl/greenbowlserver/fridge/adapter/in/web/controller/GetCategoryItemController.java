package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.GetCategoryItemBySequenceResponse;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.GetCategoryItemResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetDefaultCategoryItemUseCase;
import com.greenbowl.greenbowlserver.fridge.domain.CategoryItem;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultCategoryItem;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GetCategoryItemController {
    private final GetCategoryItemUseCase getCategoryItemUseCase;
    private final GetDefaultCategoryItemUseCase getDefaultCategoryItemUseCase;

    private static final String GET_CATEGORY_ITEM = "카테고리 리스트 조회";
    private static final String GET_CATEGORY_ITEM_DESCRIPTION =
            "로그인 후 카테고리 리스트를 조회할 수 있습니다.";

    private static final String GET_CATEGORY_ITEM_BY_SEQUENCE = "카테고리 종류별 리스트 조회";
    private static final String GET_CATEGORY_ITEM_BY_SEQUENCE_DESCRIPTION =
            "로그인 후 카테고리 종류별 리스트를 조회할 수 있습니다.";

    @ApiOperation(value = GET_CATEGORY_ITEM_BY_SEQUENCE, notes = GET_CATEGORY_ITEM_BY_SEQUENCE_DESCRIPTION)
    @GetMapping("/category-items")
    public ResponseEntity<List<GetCategoryItemBySequenceResponse>> getCategoryItemsBySequence(@RequestParam("sequence") String sequence){
        String userId = "1";

        List<CategoryItem> categoryItems = getCategoryItemUseCase
                .getCategoryItemBySequence(Long.parseLong(userId), Integer.parseInt(sequence));

        List<DefaultCategoryItem> defaultCategoryItems = getDefaultCategoryItemUseCase
                .getDefaultCategoryItemBySequence(Integer.parseInt(sequence));

        List<GetCategoryItemBySequenceResponse> responses = new ArrayList<>();

        responses.addAll(
                categoryItems.stream()
                        .map(GetCategoryItemBySequenceResponse::from)
                        .collect(Collectors.toList())
        );

        responses.addAll(
                defaultCategoryItems.stream()
                        .map(GetCategoryItemBySequenceResponse::from)
                        .collect(Collectors.toList())
        );

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @ApiOperation(value = GET_CATEGORY_ITEM, notes = GET_CATEGORY_ITEM_DESCRIPTION)
    @GetMapping("/category-items/all")
    public ResponseEntity<List<GetCategoryItemResponse>> getAllCategoryItems() {
        String userId = "1";

        List<CategoryItem> responses = getCategoryItemUseCase.getCategoryItems(Long.parseLong(userId));
        List<GetCategoryItemResponse> result = responses.stream()
                .map(GetCategoryItemResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
