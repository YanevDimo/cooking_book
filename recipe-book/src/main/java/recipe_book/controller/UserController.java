package recipe_book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipe_book.model.User;
import recipe_book.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null) {
            throw new Exception("this email is already exist. Try another one!!! " + user.getEmail());
        }

        return userRepository.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) throws Exception {

        userRepository.deleteById(userId);

        return "User deleted successfully";
    }
    @GetMapping("/users")
    public List<User> getAllUsers() throws Exception {

        return userRepository.findAll();
    }


}
