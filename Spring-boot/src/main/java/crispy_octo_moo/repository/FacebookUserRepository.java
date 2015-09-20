package crispy_octo_moo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import crispy_octo_moo.domain.FbUserProfile;

public interface FacebookUserRepository extends MongoRepository<FbUserProfile, String> {

}
