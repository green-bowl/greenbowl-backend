package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.RemoveRecipeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    private static final String DELETE_BOOKMARKED_RECIPE = "회원이 북마크에 등록한 레시피 삭제";
    private static final String DELETE_BOOKMARKED_RECIPE_DESCRIPTION
            = "레시피 이름을 입력해 북마크에 등록된 레시피를 삭제할 수 있습니다.";
    private static final String RECIPE_NAME = "레시피 이름";
    private static final String RECIPE_NAME_EXAMPLE = "된장찌개";

    @ApiOperation(value = DELETE_BOOKMARKED_RECIPE, notes = DELETE_BOOKMARKED_RECIPE_DESCRIPTION)
    @DeleteMapping()
    public ResponseEntity<Void> cancelRecipe(
            @ApiParam(value = RECIPE_NAME, example = RECIPE_NAME_EXAMPLE)
            @RequestParam(value = "name") String name
    ) {
        removeRecipeUseCase.removeRecipe(name);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
