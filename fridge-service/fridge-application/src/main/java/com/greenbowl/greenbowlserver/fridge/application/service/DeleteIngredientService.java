package com.greenbowl.greenbowlserver.fridge.application.service;

import com.greenbowl.greenbowlserver.fridge.application.port.in.command.DeleteIngredientCommand;
import com.greenbowl.greenbowlserver.fridge.application.port.in.usecase.DeleteIngredientUseCase;
import com.greenbowl.greenbowlserver.fridge.application.port.out.DeleteIngredientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteIngredientService implements DeleteIngredientUseCase {
    private final DeleteIngredientPort deleteIngredientPort;
    @Override
    public void deleteIngredient(List<DeleteIngredientCommand> deleteIngredientCommands) {
        deleteIngredientPort.deleteIngredient(deleteIngredientCommands);
    }
}
