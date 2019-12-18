package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository {

    List<Pizza> getAllPizzas();

    Pizza save(Pizza pizza);

    Page<Pizza> getAllPizzas(int page, int size);

    Optional<Pizza> findById(String pizzaId);

    void deleteById(String pizzaId);

}
