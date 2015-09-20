package crispy_octo_moo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.twitter.api.Twitter;

import javax.inject.Inject;

/**
 * Created by yangboz on 15/9/20.
 */
@Configuration
public class SocialApiConfig {
    //    @Bean
//    @Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
//    public Facebook facebook(ConnectionRepository connectionRepository) {
//        Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook.class);
//        return facebook != null ? facebook.getApi() : new FacebookTemplate();
//    }
//
//    @Bean
//    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//    public Twitter twitter(ConnectionRepository connectionRepository) {
//        Connection<Twitter> twitter = connectionRepository.findPrimaryConnection(Twitter.class);
//        return twitter != null ? twitter.getApi() : new TwitterTemplate();
//    }
    @Autowired
    Environment environment;

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new LinkedInConnectionFactory(
                environment.getProperty("linkedin.consumerKey"),
                environment.getProperty("linkedin.consumerSecret")));
        return registry;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public LinkedIn LinkedIn(ConnectionRepository connectionRepository) {
        Connection<LinkedIn> linkedIn = connectionRepository.findPrimaryConnection(LinkedIn.class);
        return linkedIn != null ? linkedIn.getApi() : new LinkedInTemplate("");
    }
}