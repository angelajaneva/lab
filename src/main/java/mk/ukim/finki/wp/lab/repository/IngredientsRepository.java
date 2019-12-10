package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Ingredient;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface IngredientsRepository {

    List<Ingredient> getAllIngredients();

    Ingredient save(Ingredient ingredient);

    Page<Ingredient> getAllIngredients(int page, int size);

    Optional<Ingredient> findById(String ingredientId);

    void deleteById(String ingredientId);

}
