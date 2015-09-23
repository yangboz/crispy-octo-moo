/**
 *
 */
package crispy_octo_moo.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.repository.FacebookUserRepository;

/**
 * The Class FacebookConnectController.
 *
 * @author yangboz
 */
@RestController
@RequestMapping("/v1/connect/facebook/")
public class FacebookConnectController {

    // ==============
    // PRIVATE FIELDS
    // ==============

    private final Logger LOG = LoggerFactory.getLogger(FacebookConnectController.class);

    // Autowire an object of type UserDao
    @Autowired
    private FacebookUserRepository _fbUserDao;

    //	@Inject
//	private ConnectionRepository connectionRepository;
    private Facebook facebook;

    private ConnectionRepository connectionRepository;

    @Inject
    public FacebookConnectController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }
//
//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
//	@ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
//	public JsonObject create(@RequestBody @Valid User user) {
//		return new JsonObject(_userDao.save(user));
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	@ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
//	public JsonObject list() {
//		return new JsonObject(_userDao.findAll());
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@ApiOperation(httpMethod = "GET", value = "Response a string describing if the user info id is successfully get or not.")
//	public User get(@PathVariable("id") String id) {
//		return this._userDao.findOne(id);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	@ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  user info is successfully updated or not.")
//	public JsonObject update(@PathVariable("id") String id, @RequestBody @Valid User user) {
////		User find = this._userDao.findOne(id);
//		user.setId(id);
//		return new JsonObject(this._userDao.save(user));
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the user info is successfully delete or not.")
//	public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
//		this._userDao.delete(id);
//		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
//	}

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public JsonObject getUserProfile(@RequestBody @Valid Snap415Token snap415Token) {
        /**
         * Programmatically signs in the user with the given the user ID.
         * @see: spring-social-showcase-boot(SignInUtil)
         */
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(snap415Token.getId(), null, null));
        //
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
        LOG.info("connectionRepository.findAllConnections():" + connectionRepository.findAllConnections().toString());
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        LOG.info("Connection<Facebook>:" + connection);
        Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate(snap415Token.getToken());
        LOG.info("facebook,isAuthorized():" + facebook.isAuthorized() + "," + facebook.toString());
        //Retrieving a user's profile data.
        //@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
        org.springframework.social.facebook.api.User profile = facebook.userOperations().getUserProfile();
        //Synchronize the FB user profile to DB.
        FbUserProfile fbUser = new FbUserProfile(profile.getId(), profile.getName(), profile.getFirstName(), profile.getLastName(), profile.getGender(), profile.getLocale());
        //
        this._fbUserDao.save(fbUser);
        return new JsonObject(profile);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public JsonObject getUserPost(@RequestBody @Valid Snap415Token snap415Token) {
        /**
         * Programmatically signs in the user with the given the user ID.
         * @see: spring-social-showcase-boot(SignInUtil)
         */
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(snap415Token.getId(), null, null));
        //
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate(snap415Token.getToken());
        //Retrieving a user's profile data.
        //@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
        PagedList<org.springframework.social.facebook.api.Post> fbPosts = facebook.feedOperations().getPosts();
        //TODO:Analysis the user post contents.
        return new JsonObject(fbPosts);
    }
}
