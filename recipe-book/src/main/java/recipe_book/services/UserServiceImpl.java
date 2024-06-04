package recipe_book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipe_book.config.JwtProvider;
import recipe_book.model.User;
import recipe_book.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {

        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("user no found with id " + userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        if (email == null) {
            throw new Exception("provide valid jwt token...");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("user not found with email "+email);
        }
        return user;
    }
}
