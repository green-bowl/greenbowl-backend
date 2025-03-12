package com.greenbowl.greenbowlserver.fridge.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.mapper.FridgeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.fridge.adapter.in.web.request.DeleteIngredientRequest;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.DeleteIngredientUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class DeleteIngredientController {
    private final DeleteIngredientUseCase deleteIngredientUseCase;

    private static final String DELETE_INGREDIENT = "냉장고 재료 삭제";
    private static final String DELETE_INGREDIENT_DESCRIPTION =
            "냉장고 재료ID 를 입력하여 삭제할 수 있습니다.";
    private static final String DELETE_INGREDIENT_FORM = "냉장고 재료 삭제 양식";

    @ApiOperation(value = DELETE_INGREDIENT, notes = DELETE_INGREDIENT_DESCRIPTION)
    @DeleteMapping("/ingredients")
    public ResponseEntity<String> deleteIngredient(
            @RequestBody @Valid @ApiParam(value = DELETE_INGREDIENT_FORM) List<DeleteIngredientRequest> deleteIngredientRequest) {
        List<DeleteIngredientCommand> deleteIngredientCommands = deleteIngredientRequest.stream()
                        .map(FridgeRequestToCommandMapper::mapToCommand).collect(Collectors.toList());

        deleteIngredientUseCase.deleteIngredient(deleteIngredientCommands);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
