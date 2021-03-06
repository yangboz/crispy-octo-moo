package crispy_octo_moo.repository;

import crispy_octo_moo.domain.Snap415UserPosts;
import crispy_octo_moo.domain.Snap415UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yangboz on 15/9/21.
 */
public interface Snap415UserProfileRepository extends MongoRepository<Snap415UserProfile, String> {
    Snap415UserProfile findBySnap415ID(String Snap415ID);
}
