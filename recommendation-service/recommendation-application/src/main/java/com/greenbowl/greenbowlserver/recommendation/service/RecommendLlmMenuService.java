package com.greenbowl.greenbowlserver.recommendation.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.DetailedMenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.MenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.mapper.RecommendationCommandToDomainMapper;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmMenuUseCase;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLlmJsonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@RequiredArgsConstructor
@UseCase
/**
 * 중대한 무결성 이슈가 없고 실시간성이 중요한 LLM 스트리밍 기능의 성능 향상을 위해 Dirty Read 허용
 */
@Transactional(isolation = READ_UNCOMMITTED, readOnly = true, timeout = 20)
public class RecommendLlmMenuService implements RecommendLlmMenuUseCase {
    private final ReceiveLlmJsonPort receiveLlmJsonPort;

    @Override
    public String receiveLLMRecommendedMenu(MenuOptionsCommand menuOptionsCommand) {
        return receiveLlmJsonPort.receiveLlmResponse(
                RecommendationCommandToDomainMapper.mapToDomainEntity(menuOptionsCommand), false
        );
    }

    @Override
    public String receiveLLMRecommendedMenu(DetailedMenuOptionsCommand detailedMenuOptionsCommand) {
        return receiveLlmJsonPort.receiveLlmResponse(
                RecommendationCommandToDomainMapper.mapToDomainEntity(detailedMenuOptionsCommand), true
        );
    }
}
