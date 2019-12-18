package mk.ukim.finki.wp.lab.data;

import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static final List<Pizza> pizzas = new ArrayList<>(10);
    private final JpaPizzaRepository pizzaJpaRepository;

    public DataHolder(JpaPizzaRepository pizzaJpaRepository) {
        this.pizzaJpaRepository = pizzaJpaRepository;
    }

    @PostConstruct
    public void init() {

        pizzas.add(new Pizza("Margherita", "Margherita (tomato sauce, mozzarella)"));
        pizzas.add(new Pizza("Carbonara", "Carbonara (fresh cream, mozzarella, bacon)"));
        pizzas.add(new Pizza("Vegetariana", "Vegetariana (tomato sauce, mushrooms)"));
        pizzas.add(new Pizza("Calzone", "Calzone (Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)"));
        pizzas.add(new Pizza("Cheddar", "Cheddar (cheddar, tomato sauce)"));
        pizzas.add(new Pizza("Capricciosa", "Capricciosa (tomato sauce, cheese, ham)"));
        pizzas.add(new Pizza("Burger Classic", "Burger Classic (barbecue sauce, beef, mozzarella, onions)"));
        pizzas.add(new Pizza("Boston Barbecue", "Burger Barbecue (ham, chicken meat, onions)"));
        pizzas.add(new Pizza("Pepperoni", "Pepperoni (tomato sauce, mozzarella, sausage)"));
        pizzas.add(new Pizza("Quattro Formaggi", "Quattro Formaggi (Taleggio, Mascarpone, Gorgonzola, Parmesan)"));

        if (pizzaJpaRepository.count() == 0) {
            pizzaJpaRepository.saveAll(pizzas);
        }
    }
}
