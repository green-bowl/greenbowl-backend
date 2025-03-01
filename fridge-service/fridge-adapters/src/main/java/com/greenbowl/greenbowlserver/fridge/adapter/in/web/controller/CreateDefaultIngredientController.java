package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper.FridgeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.CreateIngredientRequest;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.response.CreateIngredientResponse;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.CreateDefaultIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
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

@RestController
@RequiredArgsConstructor
public class CreateDefaultIngredientController {
    private final CreateDefaultIngredientUseCase createDefaultIngredientUseCase;

    private static final String CREATE_DEFAULT_INGREDIENT = "냉장고 기본 카테고리 재료 추가";
    private static final String CREATE_DEFAULT_INGREDIENT_DESCRIPTION =
            "기본 카테고리 ID, 수량(quantity), 보관방법(storageMethod), 만료일(expirationDate)를 입력받아 추가할 수 있습니다.";
    private static final String CREATE_DEFAULT_INGREDIENT_FORM = "냉장고 기본 카테고리 재료 추가 양식";

    @ApiOperation(value = CREATE_DEFAULT_INGREDIENT, notes = CREATE_DEFAULT_INGREDIENT_DESCRIPTION)
    @PostMapping(value = "/default-ingredients")
    public ResponseEntity<List<CreateIngredientResponse>> createDefaultIngredients(
            @RequestBody @Valid @ApiParam(value = CREATE_DEFAULT_INGREDIENT_FORM) List<CreateIngredientRequest> createIngredientRequests
            ) {
        String userId = "1";
        List<CreateIngredientCommand> command = createIngredientRequests.stream()
                .map(FridgeRequestToCommandMapper::mapToCommand).collect(Collectors.toList());

        List<DefaultIngredient> defaultIngredients = createDefaultIngredientUseCase.createDefaultIngredients(Long.parseLong(userId), command);

        List<CreateIngredientResponse> responses = defaultIngredients.stream()
                .map(CreateIngredientResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }
}
