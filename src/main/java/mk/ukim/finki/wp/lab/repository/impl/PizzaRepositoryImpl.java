package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.data.DataHolder;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.repository.PizzaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.JpaPizzaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {

    private final JpaPizzaRepository jpaPizzaRepository;

    public PizzaRepositoryImpl(JpaPizzaRepository jpaPizzaRepository) {
        this.jpaPizzaRepository = jpaPizzaRepository;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(DataHolder.pizzas);
    }

    @Override
    public Pizza save(Pizza pizza) {
        return jpaPizzaRepository.save(pizza);
    }

    @Override
    public Page<Pizza> getAllPizzas(int page, int size) {
        return jpaPizzaRepository.findAll(PageRequest.of(page, size));
    }


    public Optional<Pizza> findById(String pizzaId) {
        return jpaPizzaRepository.findById(pizzaId);
    }

    @Override
    public void deleteById(String pizzaId) {
        jpaPizzaRepository.deleteById(pizzaId);
    }

}
