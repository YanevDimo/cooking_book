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

    @PostMapping("")
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

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
    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt,
                             @PathVariable Long id) throws Exception {

        User user = userService.findUserByJwt(jwt);

        return recipeService.likeRecipe(id,user);
    }

}
