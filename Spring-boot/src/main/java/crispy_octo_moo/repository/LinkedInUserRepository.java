package crispy_octo_moo.repository;

import crispy_octo_moo.domain.LiUserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yangboz on 15/9/20.
 */
public interface LinkedInUserRepository extends MongoRepository<LiUserProfile, String> {
}
