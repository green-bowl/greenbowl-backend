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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AddBookmarkRecipeController {
    private final CreateRecipeUseCase createRecipeUseCase;

    @PostMapping()
    public ResponseEntity<Void> addRecipe(
            @RequestBody AddRecipeRequest addRecipeRequest,
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        createRecipeUseCase.createRecipe(RecipeRequestToCommandMapper.mapToCommand(userId, addRecipeRequest));

        return ResponseEntity.status(CREATED).

                build();
    }

    @PostMapping("/detailed")
    public ResponseEntity<Void> addRecipe(
            @RequestBody AddDetailedRecipeRequest addDetailedRecipeRequest,
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        createRecipeUseCase.createRecipe(RecipeRequestToCommandMapper.mapToCommand(userId, addDetailedRecipeRequest));

        return ResponseEntity.status(CREATED).build();
    }
}
