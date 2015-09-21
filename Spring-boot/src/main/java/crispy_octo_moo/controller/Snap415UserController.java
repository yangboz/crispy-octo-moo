/**
 *
 */
package crispy_octo_moo.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import crispy_octo_moo.domain.Snap415User;
import crispy_octo_moo.repository.Snap415UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.repository.FacebookUserRepository;

/**
 * The Class UserController.
 *
 * @author yangboz
 */
@RestController
@RequestMapping("/v1/user/snap415")
public class Snap415UserController {

    // ==============
    // PRIVATE FIELDS
    // ==============

    // Autowire an object of type UserDao
    @Autowired
    private Snap415UserRepository _userDao;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
    public JsonObject create(@RequestBody @Valid Snap415User user) {
        return new JsonObject(_userDao.save(user));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
    public JsonObject list() {
        return new JsonObject(_userDao.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the user info id is successfully get or not.")
    public Snap415User get(@PathVariable("id") String id) {
        return this._userDao.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  user info is successfully updated or not.")
    public JsonObject update(@PathVariable("id") String id, @RequestBody @Valid Snap415User user) {
//		User find = this._userDao.findOne(id);
        user.setId(id);
        return new JsonObject(this._userDao.save(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the user info is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        this._userDao.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
