package crispy_octo_moo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import crispy_octo_moo.domain.Snap415FbUser;

public interface FbUserRepository extends MongoRepository<Snap415FbUser, String> {

}
