package info.smartkit.crispy_octo_moo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import info.smartkit.crispy_octo_moo.domain.User;
import info.smartkit.crispy_octo_moo.service.UserService;

import javax.inject.Inject;

@RestController
public class UserController {

    @Inject
    private UserService userService;

      @RequestMapping(value = "/users", produces="application/json")
      @ResponseBody
        public Iterable<User> index() {
            userService.getAllUsers();
            return userService.getAllUsers();
        }

}
