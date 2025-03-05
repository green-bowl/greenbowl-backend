package com.greenbowl.greenbowlserver.recommendation.port.in.usecase;


import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.DetailedMenuOptionsCommand;
import com.greenbowl.greenbowlserver.recommendation.port.in.command.MenuOptionsCommand;

@UseCase
public interface RecommendLlmMenuUseCase {
    String receiveLLMRecommendedMenu(MenuOptionsCommand menuOptionsCommand);

    String receiveLLMRecommendedMenu(DetailedMenuOptionsCommand detailedMenuOptionsCommand);
}
