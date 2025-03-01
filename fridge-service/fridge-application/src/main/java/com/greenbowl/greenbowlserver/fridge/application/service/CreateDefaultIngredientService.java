package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.CreateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.mapper.DefaultIngredientCommandToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.CreateDefaultIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.CreateDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateDefaultIngredientService implements CreateDefaultIngredientUseCase {
    private final CreateDefaultIngredientPort createDefaultIngredientPort;

    @Override
    public List<DefaultIngredient> createDefaultIngredients(Long userId, List<CreateIngredientCommand> command) {
        List<DefaultIngredient> defaultIngredients = command.stream()
                .map(DefaultIngredientCommandToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
        return createDefaultIngredientPort.saveDefaultIngredient(userId, defaultIngredients);
    }
}
