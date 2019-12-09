package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPizzaRepository extends JpaRepository<Pizza, String> {
}
