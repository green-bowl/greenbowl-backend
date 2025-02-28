package com.greenbowl.greenbowlserver.recipe.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.recipe.port.in.command.CreateRecipeCommand;
import com.greenbowl.greenbowlserver.recipe.port.in.mapper.RecipeCommandToDomainMapper;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.CreateRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.out.SaveRecipePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@RequiredArgsConstructor
@UseCase
/**
 * 중대한 무결성 이슈가 없고 실시간성이 중요한 북마크 기능의 성능 향상을 위해 Dirty Read 허용
 */
@Transactional(isolation = READ_UNCOMMITTED, timeout = 20)
public class CreateRecipeService implements CreateRecipeUseCase {
    private final SaveRecipePort saveRecipePort;

    @Override
    public void createRecipe(CreateRecipeCommand createRecipeCommand) {
        saveRecipePort.saveRecipe(RecipeCommandToDomainMapper.mapToDomainEntity(createRecipeCommand));
    }
}
