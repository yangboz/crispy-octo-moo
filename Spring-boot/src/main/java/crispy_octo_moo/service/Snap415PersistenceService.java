package crispy_octo_moo.service;

import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.dto.Snap415Token;

public interface Snap415PersistenceService {

	//  corresponds to Snap415UserProfile entity
	Snap415UserProfile persistUserProfile(Snap415Token token);

	Snap415UserTaxEvents persistUserPosts(Snap415Token token);

	//corresponds to Snap415UserTaxEvents entity
	Snap415UserDeals persistUserTaxEvents(Snap415Token token);

	//corresponds to Snap415UserDeals entity
	Snap415UserDeals persistUserDeals(Snap415Token token);
}
