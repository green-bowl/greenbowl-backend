package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.UpdateDefaultIngredientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateDefaultIngredientController {
    private final UpdateDefaultIngredientUseCase updateDefaultIngredientUseCase;

    private static final String UPDATE_DEFAULT_INGREDIENT = "냉장고 기본 카테고리 재료 수정";
    private static final String UPDATE_DEFAULT_INGREDIENT_DESCRIPTION =
            "냉장고 기본 재료ID, 기본 카테고리 ID, 수량(quantity), 보관방법(storageMethod), 만료일(expirationDate) 를 입력하여 수정할 수 있습니다.";
    private static final String UPDATE_DEFAULT_INGREDIENT_FORM = "냉장고 기본 카테고리 재료 수정 양식";

//    @ApiOperation(value = UPDATE_DEFAULT_INGREDIENT, notes = UPDATE_DEFAULT_INGREDIENT_DESCRIPTION)
//    @PutMapping("/default-ingredients")
//    public ResponseEntity<List<UpdateIngredientResponse>> updateIngredient(
//            @RequestBody @Valid @ApiParam(value = UPDATE_DEFAULT_INGREDIENT_FORM) List<UpdateIngredientRequest> updateIngredientRequest) {
//        String userId = "1";
//
//        List<UpdateIngredientCommand> updateIngredientCommands = updateIngredientRequest.stream()
//                .map(FridgeRequestToCommandMapper::mapToCommand).collect(Collectors.toList());
//
//        List<DefaultIngredientResult> ingredientResults = updateDefaultIngredientUseCase
//                .updateIngredients(Long.parseLong(userId), updateIngredientCommands);
//
//        List<UpdateIngredientResponse> responses = ingredientResults.stream()
//                .map(UpdateIngredientResponse::from).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(responses);
//    }
}
