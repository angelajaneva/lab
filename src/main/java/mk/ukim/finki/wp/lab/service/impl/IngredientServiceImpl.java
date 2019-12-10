package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.model.exceptions.DuplicateIngredientException;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidIngredientException;
import mk.ukim.finki.wp.lab.model.exceptions.SpicyIngredientException;
import mk.ukim.finki.wp.lab.repository.IngredientsRepository;
import mk.ukim.finki.wp.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository repository;

    public IngredientServiceImpl(IngredientsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ingredient> listIngredients() {
        return repository.getAllIngredients();
    }

    @Override
    public Ingredient updateIngredient(String name, boolean spicy, float amount, boolean veggie) {

        this.validateIngredient(name);

        if (spicy)
            this.spicyIngredientsValidation();

        Ingredient ingredient = repository.findById(name).orElseThrow(InvalidIngredientException::new);
        ingredient.setSpicy(spicy);
        ingredient.setAmount(amount);
        ingredient.setVeggie(veggie);
        return ingredient;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {

        this.validateIngredient(ingredient.getName());

        if (ingredient.isSpicy())
            this.spicyIngredientsValidation();

        return repository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String name) {
        repository.deleteById(name);
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return repository.getAllIngredients(page, size);
    }

    @Override
    public Ingredient findById(String ingredientId) {
        return repository.findById(ingredientId).orElseThrow(InvalidIngredientException::new);
    }

    @Override
    public List<Ingredient> getSpicyIngredients() {
        List<Ingredient> ingredients = this.listIngredients();
       return ingredients.stream()
                .filter(Ingredient::isSpicy)
               .collect(Collectors.toList());
    }

    @Override
    public List<Pizza> pizzasWithIngredient(Ingredient ingredient) {
        return ingredient.getPizzas();
    }

    @Override
    public void validateIngredient(String name) {

        boolean contains = listIngredients().stream()
                .anyMatch(ingredient -> ingredient.getName().equals(name));

        if (contains)
            throw new DuplicateIngredientException();
    }

    @Override
    public void spicyIngredientsValidation() {
       if (listIngredients().stream()
               .filter(Ingredient::isSpicy)
               .count() == 3)
           throw new SpicyIngredientException();
    }
}
