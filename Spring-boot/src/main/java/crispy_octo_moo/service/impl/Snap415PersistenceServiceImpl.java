package crispy_octo_moo.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;

import crispy_octo_moo.domain.Snap415TaxEvent;
import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserPosts;
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
import crispy_octo_moo.service.Snap415UserService;

@Service
public class Snap415PersistenceServiceImpl implements Snap415PersistenceService {

	 private final Logger LOG = LoggerFactory.getLogger(Snap415PersistenceServiceImpl.class);
	 
	 private String Snap415ID = null;
	
	 @Autowired
	 Snap415UserService snap415UserService;
	
	 @Autowired
	 FacebookUserService fbUserService;
	
	
	 @Autowired
	 Snap415UserProfileRepository _userProfileDao;
	 
	 @Autowired
	 Snap415UserPostsRepository _userPostsDao;
	 
	 @Autowired
	 Snap415UserTaxEventsRepository _userTaxEventsDao;
	 
	 @Autowired
	 Snap415UserDealsRepository _userDealsDao;
	 
	 
	    
	@Override
	public Snap415UserProfile persistUserProfile(Snap415Token token) {
		// TODO Auto-generated method stub
		
		Snap415UserProfile snap415UserProfile = snap415UserService.getMe(token);
		
		//LOG.info("snap415UserProfile.getSimplyBirthday():" + snap415UserProfile.getSimplyBirthday());
		
		_userProfileDao.save(snap415UserProfile);
		
		Snap415ID = snap415UserProfile.getId();
		
		return snap415UserProfile;
	}
	
	@Override
	public Snap415UserPosts persistUserPosts(Snap415Token token) {
		// TODO Auto-generated method stub
		
		PagedList<Post> fbposts = fbUserService.getUserPost(token);
		
		Snap415UserPosts snap415UserPosts = new Snap415UserPosts();
		
		snap415UserPosts.setPosts(fbposts);
		snap415UserPosts.setSnap415ID(Snap415ID);
		
		_userPostsDao.save(snap415UserPosts);
		
		return snap415UserPosts;
		
		
	}
	
	@Override
	public Snap415UserTaxEvents persistUserTaxEvents(Snap415Token token) {
		// TODO Auto-generated method stub
		// The initial persistence is done by retrieving story property in fb post and saving it as tax event
		
		Snap415UserTaxEvents snap415UserTaxEvents = new Snap415UserTaxEvents();
		
		LOG.info("User Tax Events:" + Snap415ID);

		if(Snap415ID != null)
		{
			PagedList<Post> fbposts = snap415UserService.getFBPosts(Snap415ID);
			
			if(!fbposts.isEmpty())
			{
				ArrayList<Snap415TaxEvent> snap415TaxEvents = new ArrayList<Snap415TaxEvent>();
				
				for(Post temp : fbposts)
				{
					Snap415TaxEvent snap415TaxEvent = new Snap415TaxEvent();
					
					snap415TaxEvent.setTaxCategory("unknown");
					snap415TaxEvent.setTaxCredit("unknown");
					snap415TaxEvent.setEventDescription(temp.getStory());
					
					snap415TaxEvents.add(snap415TaxEvent);
				}
				
				snap415UserTaxEvents.setSnap415ID(Snap415ID);
				snap415UserTaxEvents.setTaxEvents(snap415TaxEvents);
				
				_userTaxEventsDao.save(snap415UserTaxEvents);
			}
			
		}
		
		return null;
	}
	
	@Override
	public Snap415UserDeals persistUserDeals(Snap415Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
