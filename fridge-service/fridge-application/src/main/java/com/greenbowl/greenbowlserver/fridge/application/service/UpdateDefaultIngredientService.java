package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.DefaultIngredientResult;
import com.greenbowl.greenbowlserver.fridge.application.port.in.command.UpdateIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.mapper.DefaultIngredientCommandToDomainMapper;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.UpdateDefaultIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.UpdateDefaultIngredientPort;
import com.greenbowl.greenbowlserver.fridge.domain.DefaultIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateDefaultIngredientService implements UpdateDefaultIngredientUseCase {
    private final UpdateDefaultIngredientPort updateDefaultIngredientPort;
    @Override
    public List<DefaultIngredientResult> updateIngredients(Long userId, List<UpdateIngredientCommand> updateIngredientCommands) {
        List<DefaultIngredient> defaultIngredients = updateIngredientCommands.stream()
                .map(DefaultIngredientCommandToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
        return updateDefaultIngredientPort.updateDefaultIngredients(defaultIngredients);
    }
}
