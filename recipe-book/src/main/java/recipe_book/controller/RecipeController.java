package recipe_book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipe_book.model.Recipe;
import recipe_book.model.User;
import recipe_book.services.RecipeService;
import recipe_book.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {

        User user = userService.findUserById(userId);

        return recipeService.createRecipe(recipe, user);
    }

    @GetMapping("/")
    public List<Recipe> getAllRecipe() throws Exception {

        return recipeService.findAllRecipe();
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {

        recipeService.deleteRecipe(recipeId);
        return "recipe deleted successfully";
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe,
                               @PathVariable Long id) throws Exception {

        return recipeService.updateRecipe(recipe, id);
    }
    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId,
                             @PathVariable Long id) throws Exception {

        User user = userService.findUserById(userId);

        return recipeService.likeRecipe(id,user);
    }

}
