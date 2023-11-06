package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.model.Recipe;
import recipes.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
}
