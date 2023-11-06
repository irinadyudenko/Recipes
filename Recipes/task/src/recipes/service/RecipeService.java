package recipes.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.NewRecipeResponseDto;
import recipes.dto.RecipeDto;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.repository.RecipeRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecipeService {
    private final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    public RecipeDto getRecipe(Integer id) {

        Recipe recipeById = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(recipeById, RecipeDto.class);
    }

    @Transactional
    public NewRecipeResponseDto postNewRecipe(RecipeDto recipeDto, String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Recipe recipe = modelMapper.map(recipeDto, Recipe.class);
        recipe.setDate(LocalDateTime.now());
        recipe.setUser(user);
        recipe = recipeRepository.save(recipe);

        return new NewRecipeResponseDto(recipe.getId());
    }

    @Transactional
    public void deleteRecipe(Integer id, String email) {
        Recipe recipeOptional = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!recipeOptional.getUser().getEmail().equals(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        try {
            recipeRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<RecipeDto> findByCategory(String category) {
        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category).stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .forEach(recipeDtos::add);
        return recipeDtos;
    }

    public List<RecipeDto> findByName(String name) {
        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name).stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .forEach(recipeDtos::add);
        return recipeDtos;
    }

    @Transactional
    public RecipeDto updateRecipe(Integer id, RecipeDto recipeDto, String email) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isEmpty()) {
            return null;
        }
        else {
            Recipe recipe = recipeOptional.get();
            if (!recipe.getUser().getEmail().equals(email)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
            recipe.setName(recipeDto.getName());
            recipe.setCategory(recipeDto.getCategory());
            recipe.setDate(LocalDateTime.now());
            recipe.setDescription(recipeDto.getDescription());
            recipe.setIngredients(recipeDto.getIngredients());
            recipe.setDirections(recipeDto.getDirections());
            return modelMapper.map(recipeRepository.save(recipe), RecipeDto.class);
        }
    }
}
