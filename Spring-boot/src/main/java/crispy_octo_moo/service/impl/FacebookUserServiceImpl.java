package crispy_octo_moo.service.impl;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.repository.FacebookUserRepository;
import crispy_octo_moo.service.FacebookUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by yangboz on 9/23/15.
 */
@Service
public class FacebookUserServiceImpl implements FacebookUserService {

    private final Logger LOG = LoggerFactory.getLogger(FacebookUserServiceImpl.class);

    // Autowire an object of type UserDao
    @Autowired
    private FacebookUserRepository _fbUserDao;

    //	@Inject
//	private ConnectionRepository connectionRepository;
    private Facebook facebook;

    private ConnectionRepository connectionRepository;

    @Inject
    public FacebookUserServiceImpl(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @Override
    public User getUserProfile(Snap415Token token) {
        /**
         * Programmatically signs in the user with the given the user ID.
         * @see: spring-social-showcase-boot(SignInUtil)
         */
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(token.getId(), null, null));
        //
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
        LOG.debug("connectionRepository.findAllConnections():" + connectionRepository.findAllConnections().toString());
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        LOG.debug("Connection<Facebook>:" + connection);
        Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate(token.getToken());
        LOG.debug("facebook,isAuthorized():" + facebook.isAuthorized() + "," + facebook.toString());
        //Retrieving a user's profile data.
        //@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
        //@see:
        org.springframework.social.facebook.api.User profile = facebook.userOperations().getUserProfile();
//        LOG.info("Raw facebook user profile:" + profile.toString());
        LOG.info("Raw facebook user profile,getBirthday:" + profile.getBirthday() + ",getEducation:" + profile.getEducation().toArray().toString()
                + ",getWork" + profile.getWork().toArray().toString() + ",getRelationshipStatus:" + profile.getRelationshipStatus());
        //Synchronize the FB user profile to DB.
//        FbUserProfile fbUser = new FbUserProfile(profile.getId(), profile.getName(), profile.getFirstName(), profile.getLastName(), profile.getGender(), profile.getLocale());
        //
        return profile;
//        return this._fbUserDao.save(fbUser);
    }

    @Override
    public PagedList<Post> getUserPost(Snap415Token token) {
        /**
         * Programmatically signs in the user with the given the user ID.
         * @see: spring-social-showcase-boot(SignInUtil)
         */
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(token.getId(), null, null));
        //
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate(token.getToken());
        //Retrieving a user's profile data.
        //@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
        PagedList<Post> fbPosts = facebook.feedOperations().getPosts();
        //TODO:Analysis the user post contents.
        //And after analysis, construct Snap415UserTaxEvents and Snap415UserDeals
        return fbPosts;
    }
}
