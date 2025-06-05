package ch.fhnw.brew.data.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeID;

    @Column(nullable = false)
    @NotBlank(message = "Recipe name is required")
    private String recipeName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Recipe category is required")
    private RecipeCategory recipeCategory;

    public Integer getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Integer recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeCategory getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(RecipeCategory recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    @Transient
    public String getRecipeCategoryLabel() {
        return recipeCategory != null ? recipeCategory.getLabel() : null;
    }
}
