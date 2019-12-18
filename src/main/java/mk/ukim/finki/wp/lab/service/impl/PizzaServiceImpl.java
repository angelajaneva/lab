package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidPizzaException;
import mk.ukim.finki.wp.lab.model.exceptions.NoVeggieIngredientException;
import mk.ukim.finki.wp.lab.repository.PizzaRepository;
import mk.ukim.finki.wp.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;


    @Override
    public List<Pizza> listPizzas() {
        return pizzaRepository.getAllPizzas();
    }

    @Override
    public Pizza updatePizza(String name, String description, List<Ingredient> ingredients, boolean veggie) {
        Pizza pizza = pizzaRepository.findById(name).orElseThrow(InvalidPizzaException::new);
        pizza.setDescription(description);
        pizza.setIngredients(ingredients);
        pizza.setVeggie(veggie);

        this.veggiePizzaValidation(pizza);

        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza createPizza(Pizza pizza) {
        this.veggiePizzaValidation(pizza);
        return pizzaRepository.save(pizza);
    }

    @Override
    public void deletePizza(String name) {
        pizzaRepository.deleteById(name);
    }

    @Override
    public Page<Pizza> getAllPizzas(int page, int size) {
        return pizzaRepository.getAllPizzas(page, size);
    }

    @Override
    public Pizza findById(String pizzaId) {
        return pizzaRepository.findById(pizzaId).orElseThrow(InvalidPizzaException::new);
    }

    @Override
    public void veggiePizzaValidation(Pizza pizza) {

        if (pizza.isVeggie()) {
            boolean veggie = pizza.getIngredients().stream()
                    .allMatch(Ingredient::isVeggie);
            if (!veggie)
                throw new NoVeggieIngredientException();
        }
    }

    @Override
    public List<Ingredient> sameIngredients(Pizza pizza1, Pizza pizza2) {

        return pizza1.getIngredients().stream()
                .filter(pizza2.getIngredients()::contains)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pizza> lessIngredients(int totalIngredients) {
        return listPizzas().stream()
                .filter(pizza -> pizza.getIngredients().size() < totalIngredients)
                .collect(Collectors.toList());
    }
}
