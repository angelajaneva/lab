package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.Ingredient;
import mk.ukim.finki.wp.lab.repository.IngredientsRepository;
import mk.ukim.finki.wp.lab.repository.jpa.JpaIngredientsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IngredientsRepositoryImpl implements IngredientsRepository {

    private final JpaIngredientsRepository repository;

    public IngredientsRepositoryImpl(JpaIngredientsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Ingredient> findById(String ingredientId) {
        return repository.findById(ingredientId);
    }

    @Override
    public void deleteById(String ingredientId) {
        repository.deleteById(ingredientId);
    }
}
