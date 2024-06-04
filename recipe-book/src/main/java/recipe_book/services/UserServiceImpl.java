package recipe_book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_book.model.User;
import recipe_book.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long userId) throws Exception {

        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()){
            return opt.get();
        }
        throw new Exception("user no found with id "+userId);
    }
}
