package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.Recipe;
import ch.fhnw.brew.data.repository.RecipeRepository;
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

    public Recipe editRecipe(Integer id, Recipe updatedRecipe) throws Exception {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new Exception("Recipe not found"));
        recipe.setRecipeName(updatedRecipe.getRecipeName());
        recipe.setRecipeCategoryName(updatedRecipe.getRecipeCategoryName());
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Integer id) throws Exception {
        if (!recipeRepository.existsById(id)) {
            throw new Exception("Recipe does not exist");
        }
        recipeRepository.deleteById(id);
    }

    public Recipe getRecipe(Integer id) throws Exception {
        return recipeRepository.findById(id).orElseThrow(() -> new Exception("Recipe not found"));
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
