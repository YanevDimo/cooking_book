package recipe_book.services;

import recipe_book.model.User;

public interface UserService {
    public User findUserById(Long userId)throws Exception;
}
