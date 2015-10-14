/**
 *
 */
package crispy_octo_moo.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.dto.Snap415UserProfileBase;
import crispy_octo_moo.repository.Snap415UserProfileRepository;
import crispy_octo_moo.service.EITCCreditService;
import crispy_octo_moo.service.Snap415UserTaxEventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import crispy_octo_moo.dto.JsonObject;

/**
 * The Class Snap415UserProfileController.
 *
 * @author yangboz
 */
@RestController
@RequestMapping("/v1/user/profile")
public class Snap415UserProfileController {

    // ==============
    // PRIVATE FIELDS
    // ==============

    private final Logger LOG = LoggerFactory.getLogger(Snap415UserProfileController.class);

    
    // Autowire an object of type UserDao
    @Autowired
    private Snap415UserProfileRepository _userDao;

    @Autowired
    private Snap415UserTaxEventsService snap415UserTaxEventsService;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
    public Snap415UserProfile create(@RequestBody @Valid Snap415UserProfile user) {
        return _userDao.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
    public JsonObject list() {
        return new JsonObject(_userDao.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the user info id is successfully get or not.")
    public Snap415UserProfile get(@PathVariable("id") String id) {
        return this._userDao.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  user info is successfully updated or not.")
    public Snap415UserProfile update(@PathVariable("id") String id, @RequestBody Snap415UserProfileBase user) {
        Snap415UserProfile findProfile = this._userDao.findOne(id);
        Snap415UserProfileBase findProfileBase = findProfile.getProfileBase();
//        findProfile.setId(id);
//        findProfile.setSnap415ID(id);//Currently using FB ID.
        
        LOG.info("data to update:"+user.getRwIncome()+" "+user.getRwNumberOfChildren());
        findProfileBase.setRwIncome(user.getRwIncome());
        findProfileBase.setRwNumberOfChildren(user.getRwNumberOfChildren());
        findProfileBase.setRwTaxFilingStatus(user.getRwTaxFilingStatus());
        //
        findProfile.setProfileBase(findProfileBase);
  
        
        findProfile = this._userDao.save(findProfile);
        
        LOG.info("respond to save action");
        snap415UserTaxEventsService.UpdateEITECCredit(findProfile.getSnap415ID());
        
        //return this._userDao.save(findProfile);
        return findProfile;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the user info is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        this._userDao.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
