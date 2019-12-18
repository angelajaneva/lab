package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Pizzas")
public class Pizza {

    @Id
    private String name;
    private String description;

    @ManyToMany
    private List<Ingredient> ingredients;
    private boolean veggie;


    public Pizza(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
