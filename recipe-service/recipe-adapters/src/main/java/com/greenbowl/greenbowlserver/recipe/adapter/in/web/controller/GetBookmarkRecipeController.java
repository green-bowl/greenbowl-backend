package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.common.utility.FormatConverter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.response.GetRecipeResponse;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetBookmarkRecipeController {
    private final GetRecipeUseCase getRecipeUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<GetRecipeResponse> getRecipe(@PathVariable(value = "id") String id) {
        Recipe recipe = getRecipeUseCase.getRecipe(FormatConverter.parseToLong(id));

        return ResponseEntity.status(OK).body(GetRecipeResponse.from(recipe));
    }
}
