package crispy_octo_moo.controller;

//import com.google.code.linkedinapi.client.LinkedInApiClient;
//import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
//import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
//import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
//import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
//import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.domain.LiUserProfile;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.LiUserConnection;
import crispy_octo_moo.dto.UserInfo;
import crispy_octo_moo.repository.LinkedInUserRepository;
import jdk.nashorn.internal.runtime.URIUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
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
import org.springframework.web.util.UriUtils;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


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
//    private LinkedInRequestToken sessionRequestToken = null;
    private Token sessionRequestToken = null;

    @RequestMapping(value = "/access", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public JsonObject getAccessToken(@RequestBody @Valid UserInfo userInfo) {
        String linkedinKey = "77nayor82qqip3";    //add your LinkedIn key
        String linkedinSecret = "UJOUycxP5UgdD3da"; //add your LinkedIn Secret
        //@see: https://github.com/fernandezpablo85/scribe-java/wiki/getting-started
        //1
        OAuthService service = new ServiceBuilder()
                .provider(LinkedInApi.class)
                .apiKey(linkedinKey)
                .apiSecret(linkedinSecret)
                .scope("r_basicprofile")
                .callback("http://localhost:8083/api/static/oauthcallback.html")
                .build();
        //2
        Token requestToken = service.getRequestToken();
        sessionRequestToken = new Token(requestToken.getToken(), requestToken.getSecret(), requestToken.getRawResponse());
        //3
        String authUrlStr = service.getAuthorizationUrl(requestToken);
        System.out.println("authUrlStr:" + authUrlStr);
        URL authUrl = null;
//        authUri.getRawQuery().
        try {
            authUrl = new URL(authUrlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //4
//        Verifier v = new Verifier(userInfo.getToken());
        String authUrl_token = authUrl.getQuery().split("&")[0].split("=")[1];
        System.out.println("authUrl_token:" + authUrl_token);
        Verifier v = new Verifier(authUrl_token);
        System.out.println("Verifier value:" + v.getValue());
//        Token accessToken = service.getAccessToken(requestToken, v); // the requestToken you had from step 2
        Token accessToken = service.getAccessToken(sessionRequestToken, v); // the requestToken you had from step 2
        //5
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.xml");
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = request.send();
        System.out.println(response.getBody());
//
//        LinkedInOAuthService oauthService;
//        LinkedInRequestToken requestToken;
//
//        System.out.println("Fetching request token from LinkedIn...");
//        String authUrl = null;
//        String authToken, authTokenSecret;
//
//        oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(linkedinKey, linkedinSecret);
//        requestToken = oauthService.getOAuthRequestToken();
//
//        authToken = requestToken.getToken();
//        authTokenSecret = requestToken.getTokenSecret();
//        //
//        sessionRequestToken = new LinkedInRequestToken(authToken, authTokenSecret);
//
//        System.out.println("Request token: " + requestToken);
//        System.out.println("Auth token:" + authToken);
//        System.out.println("Auth token secret:" + authTokenSecret);
//
//        authUrl = requestToken.getAuthorizationUrl();
//
//        System.out.println("Copy below link in web browser to authorize. Copy the PIN obtained:" + authUrl);
////        System.out.println("Enter the PIN code:");
//        String pin = "54769";
//
//        LinkedInAccessToken accessToken;
//        try {
//            System.out.println("Fetching access token from LinkedIn...");
//            oauthService.addRequestHeader("xoauth_oauth2_access_token", authToken);
//            accessToken = oauthService.getOAuthAccessToken(sessionRequestToken, pin);
//            System.out.println("Access token : " + accessToken.getToken());
//            System.out.println("Token secret : " + accessToken.getTokenSecret());
////            final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(linkedinKey, linkedinSecret);
////            final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
//
//            //posting status to profile
////            client.updateCurrentStatus("LinkedIn-J API is cool!");
//
//        } finally {
//            System.out.println("Got LinkedIn access token!");
////            System.out.println("Updated status!");
//        }
        return new JsonObject(accessToken);

    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the access_token related user profile is successfully received.")
    public JsonObject getUserProfile(@RequestBody @Valid UserInfo userInfo) {
        /**
         * Programmatically signs in the user with the given the user ID.
         * @see: spring-social-showcase-boot(SignInUtil)
         */
        LOG.info("userInfo:" + userInfo.toString());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo.getUserId(), null, null));
        //@see: https://developer-programs.linkedin.com/documents/exchange-jsapi-tokens-rest-api-oauth-tokens
//		String accessToken = "f8FX29g..."; // access token received from Facebook after OAuth authorization
//		Facebook facebook = new FacebookTemplate(accessToken);
        LOG.info("connectionRepository.findAllConnections():" + connectionRepository.findAllConnections().toString());
        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
//        Connection<LinkedIn> connection = connectionRepository.
//        "77nayor82qqip3", "UJOUycxP5UgdD3da"
        LOG.info("Connection<LinkedIn>:" + connection);
        LinkedIn linkedIn = connection != null ? connection.getApi() : new LinkedInTemplate(userInfo.getToken());
        LOG.info("linkedIn,isAuthorized():" + linkedIn.isAuthorized() + "," + linkedIn.toString());
        //Retrieving a user's profile data.
        //@see: http://docs.spring.io/spring-social-facebook/docs/2.0.1.RELEASE/reference/htmlsingle/
        LOG.info("linkedIn getProfileById:" + linkedIn.profileOperations().getProfileById(userInfo.getUserId()));
        System.out.println("linkedIn.profileOperations():" + linkedIn.profileOperations().toString());
        LinkedInProfile profile = linkedIn.profileOperations().getUserProfile();
        LOG.info("LinkedInProfile: id:" + profile.getId());
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

