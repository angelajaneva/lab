package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

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
    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas;
}
