package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.mapper.RecommendationRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.RecipeOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.response.ResponseGenerator;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.response.StreamingProcessor;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmRecipeUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.greenbowl.greenbowlserver.common.utility.ApiConstant.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RecommendLlmRecipeController {
    private final RecommendLlmRecipeUseCase recommendLlmRecipeUseCase;

    private static final String GET_RECIPE_LLM_SSE_STREAMING = "LLM을 통해 레시피 SSE 스트리밍 데이터를 추천 받는 엔드포인트";
    private static final String GET_RECIPE_LLM_SSE_STREAMING_DESCRIPTION
            = "LLM을 통해 레시피 SSE 스트리밍 데이터를 추천 받을 수 있습니다.";

    private static final String USED_INGREDIENT_NAMES_VALUE = "사용된 재료명 목록";
    private static final String USED_INGREDIENT_NAMES_EXAMPLE = "돼지고기, 감자, 양파";

    private static final String USED_INGREDIENT_WEIGHTS_VALUE = "사용된 재료 무게 목록";
    private static final String USED_INGREDIENT_WEIGHTS_EXAMPLE = "30g, 20g, 10g";

    @ApiOperation(value = GET_RECIPE_LLM_SSE_STREAMING, notes = GET_RECIPE_LLM_SSE_STREAMING_DESCRIPTION)
    @GetMapping(value = "/recipes/sse", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Flux<String>> requestRecipeLLM(
            @ApiParam(value = RECIPE_NAME_VALUE, defaultValue = RECIPE_NAME_EXAMPLE)
            @RequestParam List<String> name,
            @ApiParam(value = USED_INGREDIENT_NAMES_VALUE, defaultValue = USED_INGREDIENT_NAMES_EXAMPLE)
            @RequestParam List<String> usedIngredientNames,
            @ApiParam(value = USED_INGREDIENT_WEIGHTS_VALUE, defaultValue = USED_INGREDIENT_WEIGHTS_EXAMPLE)
            @RequestParam List<String> usedIngredientWeights,
            @ApiParam(value = COOKING_TIME_VALUE, defaultValue = COOKING_TIME_EXAMPLE)
            @RequestParam List<String> cookingTime,
            @ApiParam(value = CALORIES_VALUE, defaultValue = CALORIES_EXAMPLE)
            @RequestParam List<String> calories
    ) {
        Flux<String> responseFlux = recommendLlmRecipeUseCase.recieveLlmRecommendedRecipe(
                RecommendationRequestToCommandMapper.mapToCommand(
                        RecipeOptionsRequest.of(name, usedIngredientNames, usedIngredientWeights, cookingTime, calories)
                )
        );

        StreamingProcessor.proceedStreaming(responseFlux);

        return ResponseGenerator.generateResponse(responseFlux);
    }
}
