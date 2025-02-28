package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.mapper.RecipeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddDetailedRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.CreateRecipeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AddBookmarkRecipeController {
    private final CreateRecipeUseCase createRecipeUseCase;

    @PostMapping()
    public ResponseEntity<Void> addRecipe(@RequestBody AddRecipeRequest addRecipeRequest) {
        // TODO: 회원 기능 구현 후 JWT Payload 디코딩을 통한 실제 회원 ID로 대체
        String userId = "1";

        createRecipeUseCase.createRecipe(RecipeRequestToCommandMapper.mapToCommand(userId, addRecipeRequest));

        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/detailed")
    public ResponseEntity<Void> addRecipe(@RequestBody AddDetailedRecipeRequest addDetailedRecipeRequest) {
        // TODO: 회원 기능 구현 후 JWT Payload 디코딩을 통한 실제 회원 ID로 대체
        String userId = "1";

        createRecipeUseCase.createRecipe(RecipeRequestToCommandMapper.mapToCommand(userId, addDetailedRecipeRequest));

        return ResponseEntity.status(CREATED).build();
    }
}
