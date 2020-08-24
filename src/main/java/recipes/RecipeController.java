package recipes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Properties;
import java.util.Set;

@RestController
public class RecipeController {

// TO HOLD SOME YUMMY RECIPES
private Recipe[] recipes;

    //
    public RecipeController() {
     this.recipes = this.loadRecipies();
    }


    // Reads a properties file of recipes and returns an array of recipe objects
    private Recipe[] loadRecipies() {
      String propFile = "recipes.properties";
      Recipe[] recipeArray = null;
      try {
        Properties props = new Properties();
        props.load(RecipeController.class.getResourceAsStream("/" + propFile));
        Set<String> keySet = props.stringPropertyNames();
        int keySetLen = keySet.size();
        recipeArray = new Recipe[keySetLen];
        int i = 0;
        for (String keyStr : keySet) {
           recipeArray[i] = new Recipe((long)i, keyStr, props.getProperty(keyStr));
           i++;
        }
      }
      catch(Exception e){
        System.out.println("Error encountered while loading/processing properties file: " + propFile);
        e.printStackTrace();
      }
      return recipeArray;
    }


    //
    @GetMapping("/recipes")
    public Recipe[] recipes(){
        return this.recipes;
    }


    //
    @GetMapping("/recipes/id/{id}")
    public Recipe recipe(@PathVariable int id) {
        for (Recipe recipe: recipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        throw new ResponseStatusException (
                HttpStatus.NOT_FOUND, "Recipe not found"
        );
    }


    //
    @GetMapping("/recipes/title/{title}")
    public Recipe recipe(@PathVariable String title) {
        for (Recipe recipe: recipes) {
            if (recipe.getTitle().equals(title)) {
                return recipe;
            }
        }
        throw new ResponseStatusException (
                HttpStatus.NOT_FOUND, "Recipe not found"
        );
    }
}
