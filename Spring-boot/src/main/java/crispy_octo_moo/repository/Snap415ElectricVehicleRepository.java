package crispy_octo_moo.repository;

import crispy_octo_moo.domain.Snap415UserPosts;
import crispy_octo_moo.domain.Snap415ElectricVehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 */
public interface Snap415ElectricVehicleRepository extends MongoRepository<Snap415ElectricVehicle, String> {
    Snap415ElectricVehicle findBySnap415ID(String Snap415ID);
}
