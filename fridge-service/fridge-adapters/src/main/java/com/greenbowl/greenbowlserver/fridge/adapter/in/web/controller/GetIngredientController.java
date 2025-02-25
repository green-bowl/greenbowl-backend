package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.GetIngredientResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.GetIngredientUseCase;
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
public class GetIngredientController {
    private final GetIngredientUseCase getIngredientUseCase;

    private static final String GET_INGREDIENT = "냉장고 재료 조회";
    private static final String GET_CATEGORY_ITEM_DESCRIPTION =
            "로그인 후 냉장고 재료를 조회할 수 있습니다.";

    @ApiOperation(value = GET_INGREDIENT, notes = GET_CATEGORY_ITEM_DESCRIPTION)
    @GetMapping("/ingredients")
    public ResponseEntity<List<GetIngredientResponse>> getIngredient() {
        String userId = "1";

        List<IngredientResult> responses = getIngredientUseCase.getIngredients(Long.parseLong(userId));
        List<GetIngredientResponse> result = responses.stream()

                .map(GetIngredientResponse::from).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
