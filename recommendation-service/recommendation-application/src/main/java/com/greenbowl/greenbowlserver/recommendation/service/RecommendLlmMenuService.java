package com.greenbowl.greenbowlserver.recommendation.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.DetailedMenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.MenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.mapper.RecommendationCommandToDomainMapper;
import com.greenbowl.greenbowlserver.recommendation.port.in.usecase.RecommendLlmMenuUseCase;
import com.greenbowl.greenbowlserver.recommendation.port.out.ReceiveLlmJsonPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
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
