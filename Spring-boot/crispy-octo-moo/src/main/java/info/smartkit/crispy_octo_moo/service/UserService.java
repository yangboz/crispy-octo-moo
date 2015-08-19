package info.smartkit.crispy_octo_moo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.smartkit.crispy_octo_moo.dao.UserRepository;
import info.smartkit.crispy_octo_moo.domain.User;

import javax.inject.Inject;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        return userRepository.save(new User(name));
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
