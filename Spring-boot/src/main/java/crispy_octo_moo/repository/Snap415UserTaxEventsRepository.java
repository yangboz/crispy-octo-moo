package crispy_octo_moo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;

public interface Snap415UserTaxEventsRepository  extends MongoRepository<Snap415UserTaxEvents, String>{
    Snap415UserTaxEvents findBySnap415ID(String Snap415ID);

}
