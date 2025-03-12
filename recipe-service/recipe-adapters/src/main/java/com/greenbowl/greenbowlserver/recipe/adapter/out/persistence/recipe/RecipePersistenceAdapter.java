package com.greenbowl.greenbowlserver.recipe.adapter.out.persistence.recipe;

import com.greenbowl.greenbowlserver.common.adapter.out.PersistenceAdapter;
import com.greenbowl.greenbowlserver.recipe.adapter.out.mapper.RecipeJpaEntityToDomainMapper;
import com.greenbowl.greenbowlserver.recipe.domain.Recipe;
import com.greenbowl.greenbowlserver.recipe.port.out.DeleteRecipePort;
import com.greenbowl.greenbowlserver.recipe.port.out.FindRecipePort;
import com.greenbowl.greenbowlserver.recipe.port.out.SaveRecipePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@PersistenceAdapter
@RequiredArgsConstructor
public class RecipePersistenceAdapter implements SaveRecipePort, FindRecipePort, DeleteRecipePort {
    private final RecipeRepository recipeRepository;

    @Override
    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(RecipeJpaEntity.from(recipe));
    }

    @Override
    public boolean existsById(Long id) {
        return recipeRepository.existsByIdAndDeleteYnFalse(id);
    }

    @Override
    public List<Recipe> findByUserId(Long userId) {
        return recipeRepository.findByUserIdAndDeleteYnFalseOrderByModifiedAtDesc(userId)
                .stream()
                .map(RecipeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recipe> findByName(String name) {
        return recipeRepository.findByNameAndDeleteYnFalseOrderByModifiedAtDesc(name)
                .stream()
                .map(RecipeJpaEntityToDomainMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return RecipeJpaEntityToDomainMapper.mapToOptionalDomainEntity(recipeRepository.findByIdAndDeleteYnFalse(id));
    }

    @Override
    public void deleteById(Long id) {
        RecipeJpaEntity recipeJpaEntity = recipeRepository.findByIdAndDeleteYnFalse(id);
        recipeJpaEntity.delete();
    }

    @Override
    public void deleteByName(String name) {
        List<RecipeJpaEntity> recipeJpaEntities = recipeRepository.findByNameAndDeleteYnFalseOrderByModifiedAtDesc(name);
        recipeJpaEntities.forEach(RecipeJpaEntity::delete);
    }
}
