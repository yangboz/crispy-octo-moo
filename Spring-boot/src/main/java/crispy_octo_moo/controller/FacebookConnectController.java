/**
 *
 */
package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.Constants;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.service.FacebookUserService;
import crispy_octo_moo.service.Snap415PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @Autowired
    FacebookUserService fbUservice;

    @Autowired
    Snap415PersistenceService snap415PersistenceService;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public User getUserProfile(@RequestBody @Valid Snap415Token snap415Token) {
        LOG.info(String.format("[getUserProfile(%1$s)...", snap415Token));
        snap415PersistenceService.persistUserProfile(snap415Token);
        snap415PersistenceService.persistUserPosts(snap415Token);
        snap415PersistenceService.persistUserTaxEvents(snap415Token);
        //Keep it as  global variables
        Snap415Token anewSnap415Token = new Snap415Token(snap415Token.getId(), snap415Token.getToken(), snap415Token.getProvider());
        Constants.snap415Tokens.add(anewSnap415Token);//TODO:logout remove item handlers;
        return fbUservice.getUserProfile(snap415Token);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public PagedList<Post> getUserPost(@RequestBody @Valid Snap415Token snap415Token) {
        return fbUservice.getUserPost(snap415Token);
    }
}
