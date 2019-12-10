package mk.ukim.finki.wp.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient {

    @Id
    private String name;
    private boolean spicy;
    private float amount;
    private boolean veggie;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Pizza> pizzas;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, spicy, amount, veggie, pizzas);
    }

}
