package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.mapper.RecipeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddDetailedRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.AddRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.CreateRecipeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.ID_EXAMPLE;
import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.USER_ID;
import static org.springframework.http.HttpStatus.CREATED;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AddBookmarkRecipeController {
    private final CreateRecipeUseCase createRecipeUseCase;

    private static final String ADD_RECIPE_TO_BOOKMARK = "회원 북마크에 레시피 등록";
    private static final String ADD_RECIPE_TO_BOOKMARK_DESCRIPTION
            = "레시피 추가 요청 양식을 전송해 회원 북마크에 레시피를 등록할 수 있습니다.";
    private static final String ADD_RECIPE_REQUEST_FORM = "레시피 추가 요청 양식";

    private static final String ADD_DETAILED_RECIPE_TO_BOOKMARK = "회원 북마크에 상세 레시피 등록";
    private static final String ADD_DETAILED_RECIPE_TO_BOOKMARK_DESCRIPTION
            = "상세 레시피 추가 요청 양식을 전송해 회원 북마크에 상세 레시피를 등록할 수 있습니다.";
    private static final String ADD_DETAILED_RECIPE_REQUEST_FORM = "상세 레시피 추가 요청 양식";

    @ApiOperation(value = ADD_RECIPE_TO_BOOKMARK, notes = ADD_RECIPE_TO_BOOKMARK_DESCRIPTION)
    @PostMapping()
    public ResponseEntity<Long> addRecipe(
            @ApiParam(value = ADD_RECIPE_REQUEST_FORM)
            @RequestBody AddRecipeRequest addRecipeRequest,
            @ApiParam(value = USER_ID, example = ID_EXAMPLE)
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        Long id = createRecipeUseCase.createRecipe(RecipeRequestToCommandMapper.mapToCommand(userId, addRecipeRequest));

        return ResponseEntity.status(CREATED).body(id);
    }

    @ApiOperation(value = ADD_DETAILED_RECIPE_TO_BOOKMARK, notes = ADD_DETAILED_RECIPE_TO_BOOKMARK_DESCRIPTION)
    @PostMapping("/detailed")
    public ResponseEntity<Void> addRecipe(
            @ApiParam(value = ADD_DETAILED_RECIPE_REQUEST_FORM)
            @RequestBody AddDetailedRecipeRequest addDetailedRecipeRequest,
            @ApiParam(value = USER_ID, example = ID_EXAMPLE)
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        createRecipeUseCase.createRecipe(RecipeRequestToCommandMapper.mapToCommand(userId, addDetailedRecipeRequest));

        return ResponseEntity.status(CREATED).build();
    }
}
