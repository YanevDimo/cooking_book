package recipe_book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_book.model.Recipe;
import recipe_book.model.User;
import recipe_book.repository.RecipeRepository;
import recipe_book.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        Recipe createRecipe = new Recipe();
        createRecipe.setTitle(recipe.getTitle());
        createRecipe.setImage(recipe.getImage());
        createRecipe.setDescription(createRecipe.getDescription());
        createRecipe.setUser(user);
        createRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepository.save(createRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) {

        Optional<Recipe> opt = recipeRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new RuntimeException("recipe not found with this id " + id);
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {

        Recipe currentRecipe = findRecipeById(id);
        if (currentRecipe.getTitle() != null) {
            currentRecipe.setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null) {
            currentRecipe.setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null) {
            currentRecipe.setDescription(recipe.getDescription());
        }
        return recipeRepository.save(currentRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe.getLikes().contains(user.getId())) {
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }

        return recipeRepository.save(recipe);
    }
}