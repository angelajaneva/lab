package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PizzaService {

    List<Pizza> listPizzas();

    Pizza updatePizza(String name, String spicy, List<Ingredient> amount, boolean veggie);

    Pizza createPizza(Pizza pizza);

    void deletePizza(String name);

    Page<Pizza> getAllPizzas(int page, int size);

    Pizza findById(String pizzaId);

    void veggiePizzaValidation(Pizza pizza);

    List<Ingredient> sameIngredients(Pizza pizza1, Pizza pizza2);

    List<Pizza> lessIngredients(int totalIngredients);
}
