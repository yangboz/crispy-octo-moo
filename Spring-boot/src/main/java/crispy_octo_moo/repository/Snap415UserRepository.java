package crispy_octo_moo.repository;

import crispy_octo_moo.domain.Snap415User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by yangboz on 15/9/21.
 */
public interface Snap415UserRepository extends MongoRepository<Snap415User, String> {
}
