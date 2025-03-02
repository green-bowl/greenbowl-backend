package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.DeleteDefaultIngredientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteDefaultIngredientController {
    private final DeleteDefaultIngredientUseCase deleteDefaultIngredientUseCase;

    private static final String DELETE_DEFAULT_INGREDIENT = "냉장고 기본 카테고리 재료 삭제";
    private static final String DELETE_DEFAULT_INGREDIENT_DESCRIPTION =
            "냉장고 기본 카테고리 재료ID 를 입력하여 삭제할 수 있습니다.";
    private static final String DELETE_DEFAULT_INGREDIENT_FORM = "냉장고 기본 카테고리 재료 삭제 양식";

//    @ApiOperation(value = DELETE_DEFAULT_INGREDIENT, notes = DELETE_DEFAULT_INGREDIENT_DESCRIPTION)
//    @DeleteMapping("/default-ingredients")
//    public ResponseEntity<Void> deleteDefaultIngredients(
//            @RequestBody @Valid @ApiParam(value = DELETE_DEFAULT_INGREDIENT_FORM) List<DeleteIngredientRequest> deleteIngredientRequests
//            ){
//        String userId = "1";
//        List<DeleteIngredientCommand> deleteDefaultIngredientCommands = deleteIngredientRequests.stream()
//                .map(FridgeRequestToCommandMapper::mapToCommand).collect(Collectors.toList());
//
//        deleteDefaultIngredientUseCase.deleteDefaultIngredient(Long.parseLong(userId), deleteDefaultIngredientCommands);
//        return ResponseEntity.ok().build();
//    }
}
