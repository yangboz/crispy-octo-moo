package info.smartkit.crispy_octo_moo.populator;

import org.springframework.stereotype.Component;

import info.smartkit.crispy_octo_moo.service.UserService;

import javax.inject.Inject;

@Component
public class GraphPopulator {

    @Inject
    public GraphPopulator(UserService userService) {

        userService.createUser("mark");

    }

}
