package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.common.utility.FormatConverter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.response.GetDetailedRecipeResponse;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.response.GetRecipeResponse;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.ID_EXAMPLE;
import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.USER_ID;
import static org.springframework.http.HttpStatus.OK;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetBookmarkRecipeController {
    private final GetRecipeUseCase getRecipeUseCase;

    private static final String GET_USER_BOOKMARKED_RECIPES = "회원이 북마크에 등록한 레시피 목록 조회";
    private static final String GET_USER_BOOKMARKED_RECIPES_DESCRIPTION = "회원 ID를 입력해 회원의 북마크 레시피 목록을 조회할 수 있습니다.";

    private static final String GET_BOOKMARKED_RECIPE = "북마크에 등록된 상세 레시피 정보 조회";
    private static final String GET_BOOKMARKED_RECIPE_DESCRIPTION
            = "레시피 ID를 전송해 북마크에 등록된 상세 레시피 정보를 조회할 수 있습니다.";
    public static final String RECIPE_ID = "레시피 ID";

    @ApiOperation(value = GET_USER_BOOKMARKED_RECIPES, notes = GET_USER_BOOKMARKED_RECIPES_DESCRIPTION)
    @GetMapping()
    public ResponseEntity<List<GetRecipeResponse>> getUserRecipes(
            @ApiParam(value = USER_ID, example = ID_EXAMPLE)
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        List<Recipe> recipes = getRecipeUseCase.getRecipes(FormatConverter.parseToLong(userId));

        return ResponseEntity.status(OK).body(
                recipes.stream()
                        .map(GetRecipeResponse::from)
                        .collect(Collectors.toList())
        );
    }

    @ApiOperation(value = GET_BOOKMARKED_RECIPE, notes = GET_BOOKMARKED_RECIPE_DESCRIPTION)
    @GetMapping("/{id}")
    public ResponseEntity<GetDetailedRecipeResponse> getRecipe(
            @ApiParam(value = RECIPE_ID, example = ID_EXAMPLE) @PathVariable(value = "id") String id) {
        Recipe recipe = getRecipeUseCase.getRecipe(FormatConverter.parseToLong(id));

        return ResponseEntity.status(OK).body(GetDetailedRecipeResponse.from(recipe));
    }
}
