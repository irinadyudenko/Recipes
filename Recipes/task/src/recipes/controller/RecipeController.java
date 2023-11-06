package recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.RecipeDto;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Integer id) {
        RecipeDto recipeDto = recipeService.getRecipe(id);
        if (recipeDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        return ResponseEntity.ok(recipeDto);
    }

    @GetMapping("/recipe/search")
    public ResponseEntity<?> findRecipe(@RequestParam(name = "category", required = false) String category,
                                        @RequestParam(name = "name", required = false) String name) {
        if ((category == null && name == null) || (category != null && name != null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
        else if (category!=null) {
            return new ResponseEntity<>(recipeService.findByCategory(category), HttpStatus.OK);
        }
        else return new ResponseEntity<>(recipeService.findByName(name), HttpStatus.OK);
    }

    @PostMapping("/recipe/new")
    public ResponseEntity<?> postNewRecipe(@RequestBody @Valid RecipeDto recipeDto, Principal principal) {
        return ResponseEntity.ok(recipeService.postNewRecipe(recipeDto, principal.getName()));
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id, Principal principal) {
        recipeService.deleteRecipe(id, principal.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable("id") Integer id,
                                          @Valid @RequestBody RecipeDto recipeDto,
                                          Principal principal) {
        RecipeDto recipeDtoResp = recipeService.updateRecipe(id, recipeDto, principal.getName());
        if (recipeDtoResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
