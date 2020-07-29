package guru.springframework.repositories;

import guru.springframework.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Set<Recipe> findAll();
}
