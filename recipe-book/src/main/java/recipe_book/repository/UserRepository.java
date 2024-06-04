package recipe_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipe_book.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
}
