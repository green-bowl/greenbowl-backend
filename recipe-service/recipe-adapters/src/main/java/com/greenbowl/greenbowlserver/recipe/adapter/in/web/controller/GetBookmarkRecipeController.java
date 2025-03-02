package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.common.utility.FormatConverter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.response.GetDetailedRecipeResponse;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.response.GetRecipeResponse;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetBookmarkRecipeController {
    private final GetRecipeUseCase getRecipeUseCase;

    @GetMapping()
    public ResponseEntity<List<GetRecipeResponse>> getUserRecipes(
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        List<Recipe> recipes = getRecipeUseCase.getRecipes(FormatConverter.parseToLong(userId));

        return ResponseEntity.status(OK).body(
                recipes.stream()
                        .map(GetRecipeResponse::from)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDetailedRecipeResponse> getRecipe(@PathVariable(value = "id") String id) {
        Recipe recipe = getRecipeUseCase.getRecipe(FormatConverter.parseToLong(id));

        return ResponseEntity.status(OK).body(GetDetailedRecipeResponse.from(recipe));
    }
}
