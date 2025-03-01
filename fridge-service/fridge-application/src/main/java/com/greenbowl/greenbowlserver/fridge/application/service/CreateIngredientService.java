package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.mapper.IngredientCommandToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.CreateIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateIngredientService implements CreateIngredientUseCase {
    private final CreateIngredientPort createIngredientPort;

    @Override
    public List<Ingredient> createIngredient(Long userId, List<CreateIngredientCommand> createIngredientCommands) {

        List<Ingredient> ingredients = createIngredientCommands.stream()
                .map(IngredientCommandToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());

        return createIngredientPort.saveIngredient(userId, ingredients);
    }
}
