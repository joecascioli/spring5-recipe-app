package guru.springframework.repositories;

import guru.springframework.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository<U, L extends Number> extends CrudRepository<Category, Long> {
}
