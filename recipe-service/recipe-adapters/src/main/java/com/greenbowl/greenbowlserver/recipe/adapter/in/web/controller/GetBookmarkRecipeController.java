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
    public ResponseEntity<List<GetRecipeResponse>> getUserRecipes() {
        // TODO: 회원 기능 구현 후 JWT Payload 디코딩을 통한 실제 회원 ID로 대체
        String userId = "1";

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
