package crispy_octo_moo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.facebook.api.impl.ComFacebookTemplate;

//import org.springframework.social.facebook.api.impl.FacebookTemplate;

/**
 * Created by yangboz on 15/9/20.
 */
@Configuration
@EnableSocial
public class SocialApiConfig {

    private static String sqootApiKey;
    private static String sqootApiSecret;
    //
//    @Bean
//    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//    public Twitter twitter(ConnectionRepository connectionRepository) {
//        Connection<Twitter> twitter = connectionRepository.findPrimaryConnection(Twitter.class);
//        return twitter != null ? twitter.getApi() : new TwitterTemplate();
//    }
    @Autowired
    Environment environment;

    public static String getSqootApiKey() {
        return sqootApiKey;
    }

    public static void setSqootApiKey(String sqootApiKey) {
        SocialApiConfig.sqootApiKey = sqootApiKey;
    }

    public static String getSqootApiSecret() {
        return sqootApiSecret;
    }

    public static void setSqootApiSecret(String sqootApiSecret) {
        SocialApiConfig.sqootApiSecret = sqootApiSecret;
    }

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new LinkedInConnectionFactory(
                environment.getProperty("spring.social.linkedin.consumerKey"),
                environment.getProperty("spring.social.linkedin.consumerSecret")));
        //
        FacebookConnectionFactory fbcf = new FacebookConnectionFactory(
                environment.getProperty("spring.social.facebook.appId"),
                environment.getProperty("spring.social.facebook.appSecret"));
        fbcf.setScope(environment.getProperty("spring.social.facebook.scope"));
        registry.addConnectionFactory(fbcf);
        //Sqoot settings
        SocialApiConfig.setSqootApiKey(environment.getProperty("sqoot.snap415.apiKey", String.class));
        SocialApiConfig.setSqootApiSecret(environment.getProperty("sqoot.snap415.apiSecret", String.class));
        return registry;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public LinkedIn LinkedIn(ConnectionRepository connectionRepository) {
        Connection<LinkedIn> linkedIn = connectionRepository.findPrimaryConnection(LinkedIn.class);
        return linkedIn != null ? linkedIn.getApi() : new LinkedInTemplate("");//EJVLzdPSPeTqy2r2fVQapO7BSEzFg65MQaIF
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository connectionRepository) {
        Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook.class);
        return facebook != null ? facebook.getApi() : new ComFacebookTemplate("");
    }


}