package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.mapper.DefaultIngredientCommandToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.DeleteDefaultIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeleteDefaultIngredientService implements DeleteDefaultIngredientUseCase {
    private final DeleteDefaultIngredientPort deleteDefaultIngredientPort;
    @Override
    public void deleteDefaultIngredient(Long userId, List<DeleteIngredientCommand> deleteIngredientCommands) {
        List<DefaultIngredient> ingredients = deleteIngredientCommands.stream()
                .map(DefaultIngredientCommandToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());

        deleteDefaultIngredientPort.deleteDefaultIngredient(userId, ingredients);
    }
}
