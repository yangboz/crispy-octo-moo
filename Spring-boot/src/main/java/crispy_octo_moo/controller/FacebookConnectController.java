/**
 *
 */
package crispy_octo_moo.controller;

import javax.inject.Inject;
import javax.validation.Valid;

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

    @Autowired
    FacebookUserService fbUservice;

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public FbUserProfile getUserProfile(@RequestBody @Valid Snap415Token snap415Token) {
        return fbUservice.getUserProfile(snap415Token);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public PagedList<Post> getUserPost(@RequestBody @Valid Snap415Token snap415Token) {
        return fbUservice.getUserPost(snap415Token);
    }
}
