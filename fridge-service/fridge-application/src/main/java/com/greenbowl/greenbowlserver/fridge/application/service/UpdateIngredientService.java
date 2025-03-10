package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.common.application.UseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.in.IngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.UpdateIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.GetIngredientPort;
import com.greenbowl.greenbowlserver.fridge.application.port.out.UpdateIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateIngredientService implements UpdateIngredientUseCase {
    private final UpdateIngredientPort updateIngredientPort;
    private final GetIngredientPort getIngredientPort;

    @Override
    public List<IngredientResult> updateIngredients(Long userId, List<UpdateIngredientCommand> updateIngredientCommands) {
        Map<Long, UpdateIngredientCommand> updateCommandMap = updateIngredientCommands.stream()
                .collect(Collectors.toMap(UpdateIngredientCommand::getId, cmd -> cmd));

        List<Ingredient> ingredients = getIngredientPort.getIngredientsByUserId(userId).stream()
                .filter(ingredient -> updateCommandMap.containsKey(ingredient.getId()))
                .collect(Collectors.toList());

        ingredients.forEach(ingredient -> {
            UpdateIngredientCommand command = updateCommandMap.get(ingredient.getId());
            ingredient.update(
                    command.getQuantity(),
                    command.getStorageMethod(),
                    command.getExpirationDate(),
                    command.getCategoryId()
            );
        });
        return updateIngredientPort.updateIngredients(userId, ingredients);
    }
}
