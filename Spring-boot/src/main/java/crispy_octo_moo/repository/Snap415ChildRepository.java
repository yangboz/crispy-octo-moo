package crispy_octo_moo.repository;

import crispy_octo_moo.domain.Snap415UserPosts;
import crispy_octo_moo.domain.Snap415Child;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 */
public interface Snap415ChildRepository extends MongoRepository<Snap415Child, String> {
    Snap415Child findBySnap415ID(String Snap415ID);
}
