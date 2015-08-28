/**
 * 
 */
package crispy_octo_moo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import crispy_octo_moo.Constants;
import crispy_octo_moo.dto.JsonObject;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;

/**
 * The Class UserController.
 *
 * @author yangboz
 */
@RestController
@RequestMapping("/api/fb")
public class FaceBookController {

	// ==============
	// PRIVATE FIELDS
	// ==============

	// Autowire an object of type UserDao
//	@Autowired
//	private UserRepository _userDao;
	//@see:http://facebook4j.org/en/code-examples.html
	Facebook facebook = new FacebookFactory().getInstance();

//	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
//	@ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
//	public JsonObject create(@RequestBody @Valid User user) {
//		return new JsonObject(_userDao.save(user));
//	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
	public JsonObject list() {
		facebook.setOAuthAppId(Constants.FB_APP_ID, Constants.FB_APP_SECRET);
		facebook.setOAuthPermissions(Constants.FB_APP_PERMISSIONS);
//		facebook.getOAuthAccessToken(new AccessToken(accessToken, null));
		return new JsonObject(facebook.getOAuthAccessToken());
	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	@ApiOperation(httpMethod = "GET", value = "Response a string describing if the user info id is successfully get or not.")
//	public User get(@PathVariable("id") long id) {
//		return this._userDao.findOne(id);
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	@ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  user info is successfully updated or not.")
//	public JsonObject update(@PathVariable("id") long id, @RequestBody @Valid User user) {
////		User find = this._userDao.findOne(id);
//		user.setId(id);
//		return new JsonObject(this._userDao.save(user));
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	@ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the user info is successfully delete or not.")
//	public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
//		this._userDao.delete(id);
//		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
//	}
}
