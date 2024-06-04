package recipe_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
