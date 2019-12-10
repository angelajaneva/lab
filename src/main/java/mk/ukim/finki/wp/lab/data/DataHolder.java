package mk.ukim.finki.wp.lab.data;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.jpa.JpaIngredientsRepository;
import mk.ukim.finki.wp.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static final List<Pizza> pizzas = new ArrayList<>(10);
    public static final List<Ingredient> ingredients = new ArrayList<>();

    private final JpaPizzaRepository pizzaRepository;
    private final JpaIngredientsRepository ingredientsRepository;


    public DataHolder(JpaPizzaRepository pizzaRepository, JpaIngredientsRepository ingredientsRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @PostConstruct
    public void init() {

        pizzas.add(new Pizza("Margherita", "Margherita (tomato sauce, mozzarella)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Carbonara", "Carbonara (fresh cream, mozzarella, bacon)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Vegetariana", "Vegetariana (tomato sauce, mushrooms)", new ArrayList<>(), true));
        pizzas.add(new Pizza("Calzone", "Calzone (Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Cheddar", "Cheddar (cheddar, tomato sauce)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Capricciosa", "Capricciosa (tomato sauce, cheese, ham)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Burger Classic", "Burger Classic (barbecue sauce, beef, mozzarella, onions)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Boston Barbecue", "Burger Barbecue (ham, chicken meat, onions)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Pepperoni", "Pepperoni (tomato sauce, mozzarella, sausage)", new ArrayList<>(), false));
        pizzas.add(new Pizza("Quattro Formaggi", "Quattro Formaggi (Taleggio, Mascarpone, Gorgonzola, Parmesan)", new ArrayList<>(), false));

        Ingredient tomato_sauce = new Ingredient("tomato_sauce", false, (float) 20.0, true, new ArrayList<>());
        Ingredient mozzarela = new Ingredient("mozzarella", false, (float) 30.0, false, new ArrayList<>());
        Ingredient fresh_cream = new Ingredient("fresh_cream", false, (float) 20.0, true, new ArrayList<>());
        Ingredient bacon = new Ingredient("bacon", false, (float) 50.0, false, new ArrayList<>());
        Ingredient mushrooms = new Ingredient("mushrooms", false, (float) 45.0, true, new ArrayList<>());
        Ingredient ricotta = new Ingredient("ricotta", false, (float) 10.0, false, new ArrayList<>());
        Ingredient pepperoni = new Ingredient("pepperoni", true, (float) 35.0, false, new ArrayList<>());
        Ingredient pizza_sauce = new Ingredient("pizza_sauce", true, (float) 20.0, true, new ArrayList<>());
        Ingredient olive_oil = new Ingredient("olive_oil", false, (float) 10.0, true, new ArrayList<>());
        Ingredient cheddar = new Ingredient("cheddar", false, (float) 10.0, false, new ArrayList<>());
        Ingredient cheese = new Ingredient("cheese", false, (float) 50.0, false, new ArrayList<>());
        Ingredient ham = new Ingredient("ham", false, (float) 50.0, false, new ArrayList<>());
        Ingredient beef = new Ingredient("beef", false, (float) 70.0, false, new ArrayList<>());
        Ingredient chicken_meat = new Ingredient("chicken_meat", false, (float) 60.0, false, new ArrayList<>());
        Ingredient sausage = new Ingredient("sausage", false, (float) 55.0, false, new ArrayList<>());
        Ingredient Taleggio = new Ingredient("Taleggio", false, (float) 30.0, false, new ArrayList<>());
        Ingredient Mascarpone = new Ingredient("Mascarpone", false, (float) 30.0, false, new ArrayList<>());
        Ingredient Gorgonzola = new Ingredient("Gorgonzola", false, (float) 30.0, false, new ArrayList<>());
        Ingredient Parmesan = new Ingredient("Parmesan", false, (float) 30.0, false, new ArrayList<>());


        ingredients.add(tomato_sauce);
        ingredients.add(mozzarela);
        ingredients.add(fresh_cream);
        ingredients.add(bacon);
        ingredients.add(mushrooms);
        ingredients.add(ricotta);
        ingredients.add(pepperoni);
        ingredients.add(pizza_sauce);
        ingredients.add(olive_oil);
        ingredients.add(cheddar);
        ingredients.add(cheese);
        ingredients.add(ham);
        ingredients.add(beef);
        ingredients.add(chicken_meat);
        ingredients.add(sausage);
        ingredients.add(Taleggio);
        ingredients.add(Mascarpone);
        ingredients.add(Gorgonzola);
        ingredients.add(Parmesan);


        pizzas.get(0).addIngredient(tomato_sauce);
        pizzas.get(0).addIngredient(mozzarela);

        pizzas.get(1).addIngredient(fresh_cream);
        pizzas.get(1).addIngredient(bacon);
        pizzas.get(1).addIngredient(mozzarela);

        pizzas.get(2).addIngredient(tomato_sauce);
        pizzas.get(2).addIngredient(mushrooms);

        pizzas.get(3).addIngredient(ricotta);
        pizzas.get(3).addIngredient(pepperoni);
        pizzas.get(3).addIngredient(pizza_sauce);
        pizzas.get(3).addIngredient(olive_oil);

        pizzas.get(4).addIngredient(cheddar);
        pizzas.get(4).addIngredient(tomato_sauce);

        pizzas.get(5).addIngredient(tomato_sauce);
        pizzas.get(5).addIngredient(cheese);
        pizzas.get(5).addIngredient(ham);

        pizzas.get(6).addIngredient(mozzarela);
        pizzas.get(6).addIngredient(beef);

        pizzas.get(7).addIngredient(ham);
        pizzas.get(7).addIngredient(chicken_meat);

        pizzas.get(8).addIngredient(tomato_sauce);
        pizzas.get(8).addIngredient(mozzarela);
        pizzas.get(8).addIngredient(sausage);

        pizzas.get(9).addIngredient(Gorgonzola);
        pizzas.get(9).addIngredient(Parmesan);
        pizzas.get(9).addIngredient(Mascarpone);
        pizzas.get(9).addIngredient(Taleggio);


        if (pizzaRepository.count() == 0) {
            pizzaRepository.saveAll(pizzas);
            ingredientsRepository.saveAll(ingredients);
        }


    }

}
