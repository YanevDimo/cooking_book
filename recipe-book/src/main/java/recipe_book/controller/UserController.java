package recipe_book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipe_book.model.User;
import recipe_book.repository.UserRepository;
import recipe_book.services.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/profile")
    public User findUserByJwt(@RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        return user;
    }




}
