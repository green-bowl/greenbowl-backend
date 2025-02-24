package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper.FridgeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.UpdateIngredientRequest;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.UpdateIngredientResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.UpdateIngredientUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fridges")
@RequiredArgsConstructor
public class UpdateIngredientController {
    private final UpdateIngredientUseCase updateIngredientUseCase;

    private static final String UPDATE_INGREDIENT = "냉장고 재료 수정";
    private static final String UPDATE_INGREDIENT_DESCRIPTION =
            "냉장고 재료ID, 카테고리 ID, 수량(quantity), 보관방법(storageMethod), 만료일(expirationDate) 를 입력하여 수정할 수 있습니다.";
    private static final String UPDATE_INGREDIENT_FORM = "냉장고 재료 수정 양식";

    @ApiOperation(value = UPDATE_INGREDIENT, notes = UPDATE_INGREDIENT_DESCRIPTION)
    @PutMapping("/ingredients")
    public ResponseEntity<List<UpdateIngredientResponse>> updateIngredient(
            @RequestBody @Valid @ApiParam(value = UPDATE_INGREDIENT_FORM) List<UpdateIngredientRequest> updateIngredientRequest) {
        String userId = "1";

        List<UpdateIngredientCommand> updateIngredientCommands = updateIngredientRequest.stream()
                .map(FridgeRequestToCommandMapper::mapToCommand).collect(Collectors.toList());

        List<IngredientResult> ingredientResults = updateIngredientUseCase
                .updateIngredients(Long.parseLong(userId), updateIngredientCommands);

        List<UpdateIngredientResponse> responses = ingredientResults.stream()
                .map(UpdateIngredientResponse::from).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
