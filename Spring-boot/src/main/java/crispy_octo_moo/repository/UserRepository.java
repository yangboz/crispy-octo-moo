package crispy_octo_moo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import crispy_octo_moo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
