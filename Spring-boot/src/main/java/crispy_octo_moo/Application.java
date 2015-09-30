package crispy_octo_moo;

import crispy_octo_moo.consts.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

// todo: replace these three with @SpringBootApplication

//
@PropertySources({@PropertySource(value = "classpath:application-${spring.profiles.active}.properties")})
//
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
@SpringBootApplication

@EnableTransactionManagement
public class Application {

    private final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;

    /**
     * Initializes registrar.
     * <p/>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p/>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
//        app.setShowBanner(false);
//        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
//        // Check if the selected profile has been set as argument.
//        // if not the development profile will be added
//        addDefaultProfile(app, source);
        app.run(args);
    }

    /**
     * Set a default profile if it has not been set
     */
    @SuppressWarnings("unused")
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active")) {
            app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
        }
    }

}
