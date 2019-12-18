package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@Entity
@Table(name = "Pizzas")
public class Pizza {

    @Id
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
    private boolean veggie;

    public Pizza(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.getPizzas().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;
        Pizza pizza = (Pizza) o;
        return veggie == pizza.veggie &&
                Objects.equals(name, pizza.name) &&
                Objects.equals(description, pizza.description) &&
                Objects.equals(ingredients, pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, ingredients, veggie);
    }
}
