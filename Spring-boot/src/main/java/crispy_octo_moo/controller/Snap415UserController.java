package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.domain.Snap415UserDeals;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.Snap415UserTaxEvents;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.Snap415Overview;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.service.Snap415UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by yangboz on 9/23/15.
 */
@RestController
@RequestMapping("/v1/user")
public class Snap415UserController {

    // ==============
    // PRIVATE FIELDS
    // ==============

    private final Logger LOG = LoggerFactory.getLogger(Snap415UserController.class);

    // Autowire an object of type UserDao
    @Autowired
    private Snap415UserService userService;

    // ==============
    // PUBLIC METHODS
    // ==============

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public Snap415UserProfile getMe(@RequestBody @Valid Snap415Token snap415Token) {
        return userService.getMe();
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user tax events is successfully received.")
    public Snap415UserTaxEvents getEvents(@RequestBody @Valid Snap415Token snap415Token) {
        return userService.getEvents();
    }

    @RequestMapping(value = "/deals", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user deals is successfully received.")
    public Snap415UserDeals getDeals(@RequestBody @Valid Snap415Token snap415Token) {
        return userService.getDeals();
    }

    @RequestMapping(value = "/overview", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user overview is successfully received.")
    public Snap415Overview getOverview(@RequestBody @Valid Snap415Token snap415Token) {
        return userService.getOverview();
    }

}
