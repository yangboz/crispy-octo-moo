package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.domain.LiUserProfile;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.LiUserConnection;
import crispy_octo_moo.dto.UserInfo;
import crispy_octo_moo.repository.LinkedInUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.NetworkStatistics;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * The Class LinkedInConnectController.
 *
 * @author yangboz
 */
@RestController
@RequestMapping("/v1/connect/linkedin/")
public class LinkedInConnectController {

    // ==============
    // PRIVATE FIELDS
    // ==============

    private final Logger LOG = LoggerFactory.getLogger(LinkedInConnectController.class);

    // Autowire an object of type UserDao
    @Autowired
    private LinkedInUserRepository _liUserDao;

    //    @Autowired
    private LinkedIn linkedIn;

    @Inject
    private ConnectionRepository connectionRepository;

    //
    @Inject
    public LinkedInConnectController(LinkedIn linkedIn, ConnectionRepository connectionRepository) {
        this.linkedIn = linkedIn;
        this.connectionRepository = connectionRepository;
    }


    //
//    @Inject
//    public LinkedInConnectController(LinkedIn linkedIn) {
//        this.linkedIn = linkedIn;
//    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public JsonObject getUserProfile(@RequestBody @Valid UserInfo userInfo) {
        /**
         * Programmatically signs in the user with the given the user ID.
         * @see: spring-social-showcase-boot(SignInUtil)
         */
        LOG.info("userInfo:" + userInfo.toString());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo.getUserId(), null, null));
        //
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
//        "77nayor82qqip3", "UJOUycxP5UgdD3da"
        LOG.info("Connection<LinkedIn>:" + connection);
        LinkedIn linkedIn = connection != null ? connection.getApi() : new LinkedInTemplate(userInfo.getToken());
        LOG.info("linkedIn:" + linkedIn.isAuthorized() + "," + linkedIn.toString());
        //Retrieving a user's profile data.
        //@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
        LinkedInProfile profile = linkedIn.profileOperations().getUserProfile();
        //Synchronize the FB user profile to DB.
        LiUserProfile liUser = new LiUserProfile(profile.getId(), profile.getLastName(), profile.getLastName(), profile.getHeadline(), profile.getIndustry(), profile.getPublicProfileUrl(), profile.getSiteStandardProfileRequest(), profile.getPublicProfileUrl());
        //
        this._liUserDao.save(liUser);
        return new JsonObject(profile);
    }

    @RequestMapping(value = "/connections", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the user connnection is successfully received.")
    public JsonObject getConnections(@RequestBody @Valid UserInfo userInfo) {
        NetworkStatistics statistics = linkedIn.connectionOperations().getNetworkStatistics();
        LiUserConnection connection = new LiUserConnection();
        connection.setFirstDegreeCount(statistics.getFirstDegreeCount());
        connection.setSecondDegreeCount(statistics.getSecondDegreeCount());
        connection.setConnections(linkedIn.connectionOperations().getConnections());
        return new JsonObject(connection);
    }
}

