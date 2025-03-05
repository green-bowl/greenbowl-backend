package com.greenbowl.greenbowlserver.recommendation.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.mapper.RecommendationRequestToCommandMapper;
import com.greenbowl.greenbowlserver.recommendation.adapter.in.web.request.MenuOptionsRequest;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmMenuUseCase;
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

    @GetMapping()
    public ResponseEntity<String> requestMenuLLM(
            @RequestParam List<String> ingredients,
            @RequestParam List<String> cookingTimeLimit,
            @RequestParam List<String> cuisineType
    ) {
        String jsonResponse = recommendLlmMenuUseCase.receiveLLMRecommendedMenu(
                RecommendationRequestToCommandMapper.mapToCommand(
                        MenuOptionsRequest.of(ingredients, cookingTimeLimit, cuisineType)
                )
        );

        return ResponseEntity.status(OK).body(jsonResponse);
    }
}
