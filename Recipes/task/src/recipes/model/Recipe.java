package recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Name must be not blank.")
    private String name;

    @Column(name = "category")
    @NotBlank
    private String category;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "description")
    @NotBlank(message = "Description must be not blank.")
    private String description;

    @ElementCollection
    @CollectionTable(
            name="recipe_ingredient",
            joinColumns=@JoinColumn(name="recipe_id")
    )
    private List<String> ingredients = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name="recipe_direction",
            joinColumns=@JoinColumn(name="direction_id")
    )
    private List<String> directions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    private User user;

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof Recipe otherRecipe)) {
            return false;
        }
        return this.name.equals(otherRecipe.name) &&
                this.description.equals(otherRecipe.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
