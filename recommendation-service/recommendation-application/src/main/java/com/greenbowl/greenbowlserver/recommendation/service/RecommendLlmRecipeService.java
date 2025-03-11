package com.greenbowl.greenbowlserver.recommendation.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.RecipeOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.mapper.RecommendationCommandToDomainMapper;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmRecipeUseCase;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLlmStreamingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@RequiredArgsConstructor
@UseCase
/**
 * 중대한 무결성 이슈가 없고 실시간성이 중요한 LLM 스트리밍 기능의 성능 향상을 위해 Dirty Read 허용
 */
@Transactional(isolation = READ_UNCOMMITTED, readOnly = true, timeout = 100)
public class RecommendLlmRecipeService implements RecommendLlmRecipeUseCase {
    private final ReceiveLlmStreamingPort receiveLlmStreamingPort;

    @Override
    public Flux<String> recieveLlmRecommendedRecipe(RecipeOptionsCommand recipeOptionsCommand) {
        return receiveLlmStreamingPort.receiveLlmStreamingSSE(
                RecommendationCommandToDomainMapper.mapToDomainEntity(recipeOptionsCommand)
        );
    }
}
