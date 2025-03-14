package com.greenbowl.greenbowlserver.recipe.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.common.domain.exception.accessdenied.InconsistentUserException;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.domain.RecipeIngredient;
import com.greenbowl.greenbowlserver.recipe.port.in.command.ModifyRecipeCommand;
import com.greenbowl.greenbowlserver.recipe.port.in.mapper.RecipeCommandToDomainMapper;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.GetRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.in.usecase.ModifyRecipeUseCase;
import com.greenbowl.greenbowlserver.recipe.port.out.UpdateRecipePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

import static com.greenbowl.greenbowlserver.common.domain.exception.ExceptionMessage.INCONSISTENT_USER_EXCEPTION_MESSAGE;
import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@RequiredArgsConstructor
@UseCase
/**
 * 중대한 무결성 이슈가 없고 실시간성이 중요한 북마크 기능의 성능 향상을 위해 Dirty Read 허용
 */
@Transactional(isolation = READ_UNCOMMITTED, timeout = 20)
public class ModifyRecipeService implements ModifyRecipeUseCase {
    private final GetRecipeUseCase getRecipeUseCase;
    private final UpdateRecipePort updateRecipePort;

    @Override
    public void addDetailedRecipeInformation(ModifyRecipeCommand modifyRecipeCommand) {
        Recipe recipe = getRecipeUseCase.getRecipe(modifyRecipeCommand.getId());

        Long storedUserId = recipe.getUserId();
        Long receivedUserId = modifyRecipeCommand.getUserId();

        if (Objects.equals(storedUserId, receivedUserId)) {
            recipe.update(
                    modifyRecipeCommand.getOneLineIntroduction(),
                    modifyRecipeCommand.getIngredients()
                            .stream().
                            map(ingredient -> RecipeIngredient.of(
                                    ingredient.getName(), ingredient.getWeight())
                            )
                            .collect(Collectors.toList()),
                    modifyRecipeCommand.getIntroduction(),
                    RecipeCommandToDomainMapper.mapToDomain(modifyRecipeCommand.getNutrition())
            );

            updateRecipePort.updateRecipe(recipe);
        }

        throw new InconsistentUserException(String.format(INCONSISTENT_USER_EXCEPTION_MESSAGE, storedUserId, receivedUserId));
    }
}
