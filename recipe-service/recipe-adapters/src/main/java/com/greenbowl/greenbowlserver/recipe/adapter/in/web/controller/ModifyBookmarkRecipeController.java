package com.greenbowl.greenbowlserver.recipe.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.mapper.RecipeRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recipe.adapter.in.web.request.ModifyRecipeRequest;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.ModifyRecipeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.ID_EXAMPLE;
import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.USER_ID;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyBookmarkRecipeController {
    private final ModifyRecipeUseCase modifyRecipeUseCase;

    private static final String ADD_DETAILED_RECIPE_INFORMATION = "회원 북마크에 등록된 레시피에 상세 정보 추가";
    private static final String ADD_DETAILED_RECIPE_INFORMATION_DESCRIPTION
            = "레시피 수정 요청 양식을 전송해 회원 북마크에 등록된 레시피에 상세 정보를 추가할 수 있습니다.";
    private static final String MODIFY_RECIPE_REQUEST_FORM = "레시피 수정 요청 양식";

    @ApiOperation(value = ADD_DETAILED_RECIPE_INFORMATION, notes = ADD_DETAILED_RECIPE_INFORMATION_DESCRIPTION)
    @PatchMapping()
    public ResponseEntity<Void> addDetailedRecipeInformation(
            @ApiParam(value = MODIFY_RECIPE_REQUEST_FORM)
            @RequestBody ModifyRecipeRequest modifyRecipeRequest,
            @ApiParam(value = USER_ID, example = ID_EXAMPLE)
            @RequestHeader(value = "userId", required = false, defaultValue = "1") String userId
    ) {
        modifyRecipeUseCase.addDetailedRecipeInformation(
                RecipeRequestToCommandMapper.mapToCommand(userId, modifyRecipeRequest)
        );

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
