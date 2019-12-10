package mk.ukim.finki.wp.lab.web.rest;


import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientsApi {

    private final IngredientService service;

    public IngredientsApi(IngredientService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestParam String name,
                                       @RequestParam boolean spicy,
                                       @RequestParam float amount,
                                       @RequestParam boolean veggie) {

        return service.createIngredient(new Ingredient(name, spicy, amount, veggie, new ArrayList<>()));
    }

    @GetMapping //treba samo da gi sortiram
    public Page<Ingredient> getAll(@RequestParam(defaultValue = "0", required = false) int page,
                                   @RequestParam(defaultValue = "5", required = false) int size) {
        return service.getAllIngredients(page, size);
    }

    @PatchMapping("/{ingredientId}")
    public void editIngredient(@PathVariable String ingredientId,
                               @RequestParam boolean spicy,
                               @RequestParam float amount,
                               @RequestParam boolean veggie) {
        service.updateIngredient(ingredientId, spicy, amount, veggie);
    }


    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable String ingredientId) {
        service.deleteIngredient(ingredientId);
    }

    @GetMapping("/{ingredientId}")
    public Ingredient getById(@PathVariable String ingredientId) {
        return service.findById(ingredientId);
    }

    @GetMapping(params = "spicy")  //treba da se proveri spicy za dali e true
    public List<Ingredient> getSpicyIngredients(@RequestParam boolean spicy) {
        if (spicy)
            return service.getSpicyIngredients();
        else
            return null;
    }

    @GetMapping("/{ingredientId}/pizzas")
    public List<Pizza> pizzasContainingIngredient(@PathVariable String ingredientId) {
        Ingredient ingredient = service.findById(ingredientId);
        return service.pizzasWithIngredient(ingredient);
    }

}
