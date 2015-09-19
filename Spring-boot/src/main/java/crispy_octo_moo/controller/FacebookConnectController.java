/**
 * 
 */
package crispy_octo_moo.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import crispy_octo_moo.domain.Snap415FbUser;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.UserInfo;
import crispy_octo_moo.repository.FbUserRepository;

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
	
	// Autowire an object of type UserDao
	@Autowired
	private FbUserRepository _fbUserDao;
	
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
	public JsonObject getUserProfile(@RequestBody @Valid UserInfo userInfo) {
		/**
		 * Programmatically signs in the user with the given the user ID.
		 * @see: spring-social-showcase-boot(SignInUtil)
		 */
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo.getUserId(), null, null));	
		//
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate(userInfo.getToken());
		//Retrieving a user's profile data.
		//@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
		org.springframework.social.facebook.api.User profile = facebook.userOperations().getUserProfile();
		//Synchronize the FB user profile to DB.
		Snap415FbUser fbUser = new Snap415FbUser(profile.getId(), profile.getName(), profile.getFirstName(), profile.getLastName(), profile.getGender(), profile.getLocale());
		fbUser.setIncome(-1);
		fbUser.setNumberofChildren(-1);
		fbUser.setTaxfilingstatus("NIL");
		//
		this._fbUserDao.save(fbUser);
		return new JsonObject(profile);
	}
}
