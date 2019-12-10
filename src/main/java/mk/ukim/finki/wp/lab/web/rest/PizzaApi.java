package mk.ukim.finki.wp.lab.web.rest;


import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.service.PizzaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pizzas",  produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzaApi {

    private final PizzaService service;

    public PizzaApi(PizzaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createNewPizza(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam List<Ingredient> ingredients){

        return service.createPizza(new Pizza(name, description, ingredients, false));
    }

    @GetMapping
    public Page<Pizza> getAllPizzas(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(name = "page-size", defaultValue = "10", required = false) int size){
        return service.getAllPizzas(page, size);
    }

    @DeleteMapping("/{pizzaId}")
    public void deletePizza(@PathVariable String pizzaId){
        service.deletePizza(pizzaId);
    }

    @GetMapping("/{pizzaId}")
    public Pizza getPizza(@PathVariable String pizzaId){
        return service.findById(pizzaId);
    }

    @PutMapping("/{name}")
    public Pizza editPizza(@PathVariable String name,
                           @RequestParam String description,
                           @RequestParam List<Ingredient> ingredients,
                           @RequestParam boolean veggie){

        return service.updatePizza(name, description, ingredients, veggie);
    }

    @GetMapping(params = "totalIngredients")
    public List<Pizza> getPizzasWithLessIngredients(@RequestParam int totalIngredients){
        return service.lessIngredients(totalIngredients);
    }

    @GetMapping(value = "/compare")
    public List<Ingredient> sameIngredients(@RequestParam(name = "pizza1") String pizzaId1,
                                       @RequestParam (name = "pizza2") String pizzaId2){

        Pizza pizza1 = service.findById(pizzaId1);
        Pizza pizza2 = service.findById(pizzaId2);
        return service.sameIngredients(pizza1, pizza2);
    }
}
