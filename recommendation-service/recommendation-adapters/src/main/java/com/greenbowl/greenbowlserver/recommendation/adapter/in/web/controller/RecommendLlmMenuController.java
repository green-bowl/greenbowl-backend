package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.mapper.RecommendationRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.DetailedMenuOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.MenuOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmMenuUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@WebAdapter
@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class RecommendLlmMenuController {
    private final RecommendLlmMenuUseCase recommendLlmMenuUseCase;

    private static final String GET_MENU_LLM_JSON = "LLM을 통해 메뉴 정보 JSON 데이터를 추천 받는 엔드포인트";
    private static final String GET_MENU_LLM_JSON_DESCRIPTION = "LLM을 통해 메뉴 정보 데이터를 JSON 형식으로 추천 받을 수 있습니다.";

    private static final String AVAILABLE_INGREDIENTS_VALUE = "사용 가능한 재료명 목록";
    private static final String AVAILABLE_INGREDIENTS_EXAMPLE = "돼지고기, 감자, 양파";

    private static final String COOKING_TIME_LIMIT_VALUE = "조리 제한 시간";
    private static final String COOKING_TIME_LIMIT_EXAMPLE = "30분";

    private static final String CUISINE_TYPE_VALUE = "요리 유형";
    private static final String CUISINE_TYPE_EXAMPLE = "한식";

    @ApiOperation(value = GET_MENU_LLM_JSON, notes = GET_MENU_LLM_JSON_DESCRIPTION)
    @GetMapping()
    public ResponseEntity<String> requestMenuLLM(
            @ApiParam(value = AVAILABLE_INGREDIENTS_VALUE, defaultValue = AVAILABLE_INGREDIENTS_EXAMPLE)
            @RequestParam List<String> ingredients,
            @ApiParam(value = COOKING_TIME_LIMIT_VALUE, defaultValue = COOKING_TIME_LIMIT_EXAMPLE)
            @RequestParam List<String> cookingTimeLimit,
            @ApiParam(value = CUISINE_TYPE_VALUE, defaultValue = CUISINE_TYPE_EXAMPLE)
            @RequestParam List<String> cuisineType
    ) {
        String jsonResponse = recommendLlmMenuUseCase.receiveLLMRecommendedMenu(
                RecommendationRequestToCommandMapper.mapToCommand(
                        MenuOptionsRequest.of(ingredients, cookingTimeLimit, cuisineType)
                )
        );

        return ResponseEntity.status(OK).body(jsonResponse);
    }

    @GetMapping("/detailed")
    public ResponseEntity<String> requestDetailedMenuLLM(
            @RequestParam List<String> name,
            @RequestParam List<String> availableIngredients,
            @RequestParam List<String> cookingTime,
            @RequestParam List<String> calories
    ) {
        String jsonResponse = recommendLlmMenuUseCase.receiveLLMRecommendedMenu(
                RecommendationRequestToCommandMapper.mapToCommand(
                        DetailedMenuOptionsRequest.of(name, availableIngredients, cookingTime, calories)
                )
        );

        return ResponseEntity.status(OK).body(jsonResponse);
    }
}
