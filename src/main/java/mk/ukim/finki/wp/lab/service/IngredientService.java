package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IngredientService {

    List<Ingredient> listIngredients();

    Ingredient updateIngredient(String name, boolean spicy, float amount, boolean veggie);

    Ingredient createIngredient(Ingredient ingredient);

    void deleteIngredient(String name);

    Page<Ingredient> getAllIngredients(int page, int size);

    Ingredient findById(String ingredientId);

    List<Ingredient> getSpicyIngredients();

    List<Pizza> pizzasWithIngredient(Ingredient ingredient);

    void validateIngredient(String name);

    void spicyIngredientsValidation();
}
