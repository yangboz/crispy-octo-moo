package crispy_octo_moo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.repository.Snap415UserDealsRepository;
import crispy_octo_moo.repository.Snap415UserPostsRepository;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.repository.Snap415UserTaxEventsRepository;
import crispy_octo_moo.service.FacebookUserService;
import crispy_octo_moo.service.LinkedInUserService;
import crispy_octo_moo.service.Snap415PersistenceService;

public class Snap415PersistenceServiceImpl implements Snap415PersistenceService {

	 private final Logger LOG = LoggerFactory.getLogger(Snap415PersistenceServiceImpl.class);
	
	 @Autowired
	 FacebookUserService fbUserService;
	
	 @Autowired
	 LinkedInUserService linkedInUserService;
	
	 @Autowired
	 Snap415UserProfileRepository _userProfileDao;
	 
	 @Autowired
	 Snap415UserPostsRepository _userPostsDao;
	 
	 @Autowired
	 Snap415UserTaxEventsRepository _userTaxEvensDao;
	 
	 @Autowired
	 Snap415UserDealsRepository _userTDealsDao;
	 
	 
	    
	@Override
	public Snap415UserProfile persistUserProfile(Snap415Token token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Snap415UserTaxEvents persistUserPosts(Snap415Token token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Snap415UserDeals persistUserTaxEvents(Snap415Token token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Snap415UserDeals persistUserDeals(Snap415Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
