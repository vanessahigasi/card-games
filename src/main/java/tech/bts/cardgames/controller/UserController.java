package tech.bts.cardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.bts.cardgames.model.User;
import tech.bts.cardgames.repository.UserRepository;

@RestController
public class UserController {

    private UserRepository userRepository;

    //UserRepository userRepo = new UserRepository();
    //UserController userCtrl = new UserController(userRepo);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UserController() {
        userRepository = new UserRepository();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users")
    public void register(@RequestBody User user) {

        System.out.println("Registering user " + user.getName());
        userRepository.create(user);

    }
}
