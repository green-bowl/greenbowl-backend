package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper.FridgeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.CreateIngredientRequest;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.CreateIngredientResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.CreateIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CreateIngredientController {
    private final CreateIngredientUseCase createIngredientUseCase;

    private static final String CREATE_INGREDIENT = "냉장고 재료 추가";
    private static final String CREATE_INGREDIENT_DESCRIPTION =
            "카테고리 ID, 수량(quantity), 보관방법(storageMethod), 만료일(expirationDate)를 입력받아 추가할 수 있습니다.";
    private static final String CREATE_INGREDIENT_FORM = "냉장고 재료 추가 양식";

    @ApiOperation(value = CREATE_INGREDIENT, notes = CREATE_INGREDIENT_DESCRIPTION)
    @PostMapping("/ingredients")
    public ResponseEntity<List<CreateIngredientResponse>> createIngredient(
            @RequestBody @Valid @ApiParam(value = CREATE_INGREDIENT_FORM) List<CreateIngredientRequest> createIngredientRequest) {
        String userId = "1";
        List<CreateIngredientCommand> command = createIngredientRequest.stream()
                .map(FridgeRequestToCommandMapper::mapToCommand).collect(Collectors.toList());

        List<Ingredient> ingredient = createIngredientUseCase.createIngredient(Long.parseLong(userId), command);
        List<CreateIngredientResponse> responses = ingredient.stream()
                .map(CreateIngredientResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }
}
