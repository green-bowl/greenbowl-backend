package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.common.utility.FormatConverter;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.RemoveRecipeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.ID_EXAMPLE;
import static com.greenbowl.greenbowlserver.recipe.adapter.in.web.utility.ApiConstant.RECIPE_ID_VALUE;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CancelBookmarkRecipeController {
    private final RemoveRecipeUseCase removeRecipeUseCase;

    private static final String DELETE_BOOKMARKED_BY_ID_RECIPE = "레시피 ID를 통해 회원이 북마크에 등록한 레시피 삭제";
    private static final String DELETE_BOOKMARKED_RECIPE_BY_ID_DESCRIPTION
            = "레시피 ID를 입력해 북마크에 등록된 레시피를 삭제할 수 있습니다.";

    private static final String DELETE_BOOKMARKED_BY_NAME_RECIPE = "레시피 이름을 통해 회원이 북마크에 등록한 레시피 삭제";
    private static final String DELETE_BOOKMARKED_RECIPE_BY_NAME_DESCRIPTION
            = "레시피 이름을 입력해 북마크에 등록된 레시피를 삭제할 수 있습니다.";
    private static final String RECIPE_NAME = "레시피 이름";
    private static final String RECIPE_NAME_EXAMPLE = "된장찌개";

    @ApiOperation(value = DELETE_BOOKMARKED_BY_ID_RECIPE, notes = DELETE_BOOKMARKED_RECIPE_BY_ID_DESCRIPTION)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelRecipeById(
            @ApiParam(value = RECIPE_ID_VALUE, example = ID_EXAMPLE)
            @PathVariable(value = "id") String id
    ) {
        removeRecipeUseCase.removeRecipe(FormatConverter.parseToLong(id));

        return ResponseEntity.status(NO_CONTENT).build();
    }

    @ApiOperation(value = DELETE_BOOKMARKED_BY_NAME_RECIPE, notes = DELETE_BOOKMARKED_RECIPE_BY_NAME_DESCRIPTION)
    @DeleteMapping()
    public ResponseEntity<Void> cancelRecipeByName(
            @ApiParam(value = RECIPE_NAME, example = RECIPE_NAME_EXAMPLE)
            @RequestParam(value = "name") String name
    ) {
        removeRecipeUseCase.removeRecipe(name);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
