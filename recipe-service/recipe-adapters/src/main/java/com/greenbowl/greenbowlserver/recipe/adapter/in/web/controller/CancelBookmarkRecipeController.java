package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.RemoveRecipeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CancelBookmarkRecipeController {
    private final RemoveRecipeUseCase removeRecipeUseCase;

    @DeleteMapping()
    public ResponseEntity<Void> cancelRecipe(@RequestParam(value = "name") String name) {
        removeRecipeUseCase.removeRecipe(name);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
