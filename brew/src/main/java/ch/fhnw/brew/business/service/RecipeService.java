package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Recipe;
import ch.fhnw.brew.data.repository.RecipeRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe editRecipe(Integer id, Recipe updatedRecipe) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found"));

        recipe.setRecipeName(updatedRecipe.getRecipeName());
        recipe.setRecipeCategory(updatedRecipe.getRecipeCategory());

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Integer id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found"));
        recipeRepository.delete(recipe);
    }

    public Recipe getRecipe(Integer id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found"));
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
