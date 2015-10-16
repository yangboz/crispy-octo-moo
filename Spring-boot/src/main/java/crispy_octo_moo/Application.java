package crispy_octo_moo;

import crispy_octo_moo.configs.PropertiesInitializer;
import crispy_octo_moo.consts.Constants;
import crispy_octo_moo.events.EventBusHelper;
import crispy_octo_moo.events.FbPostsEventBusSubscriber;
import crispy_octo_moo.events.FbProfileEventBusSubscriber;
import crispy_octo_moo.service.Snap415PersistenceService;
import crispy_octo_moo.service.impl.Snap415PersistenceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@PropertySources({@PropertySource(value = "classpath:application-${spring.profiles.active}.properties")})
@SpringBootApplication
//@EnableTransactionManagement
//@EnableScheduling
public class Application {

    private final Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Environment env;
//@:https://spring.io/blog/2015/04/27/binding-to-data-services-with-spring-boot-in-cloud-foundry
//    @Autowired
//    private DataSource dataSource;

    //
    private static Class<Application> applicationClass = Application.class;

    /**
     * Initializes registrar.
     * <p/>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p/>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOG.warn("No Spring profile configured, running with default configuration");
        } else {
            LOG.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setShowBanner(false);
//        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
//        // Check if the selected profile has been set as argument.
//        // if not the development profile will be added
//        addDefaultProfile(app, source);
//        app.run(args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder(applicationClass)
                .initializers(new PropertiesInitializer()).run(args);
        LOG.info("ApplicationContext:" + context.getDisplayName() + context.getStartupDate());
        //
        //Global Task status monitor system start-up.
        /*
         * @see:http://stackoverflow.com/questions/310271/injecting-beans-into-a-class-outside-the-spring-managed-context
		 * https://code.google.com/p/spring-eventbus/ AlarmServiceItf
		 */
        Snap415PersistenceService snap415PersistenceService = new Snap415PersistenceServiceImpl();
        context.getAutowireCapableBeanFactory().autowireBeanProperties(
                snap415PersistenceService, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, true);
        EventBusHelper eventBusHelper = new EventBusHelper();
        context.getAutowireCapableBeanFactory().autowireBeanProperties(
                eventBusHelper, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT,
                true);
        //EventBus register
        FbPostsEventBusSubscriber fbPostsEventBusSubscriber = new FbPostsEventBusSubscriber();
        FbProfileEventBusSubscriber fbProfileEventBusSubscriber = new FbProfileEventBusSubscriber(); //
        LOG.info("snap415PersistenceService:" + snap415PersistenceService.toString());
        fbProfileEventBusSubscriber.setSnap415PersistenceService(snap415PersistenceService);
        eventBusHelper.registerSubscriber(fbPostsEventBusSubscriber);
        eventBusHelper.registerSubscriber(fbProfileEventBusSubscriber); //
//        eventBusHelper.postEvent(new MqttMessageEvent("{'TaskId':2,'BlackId':3,'FaceId':4,'Confidence':88.5,'AlarmType':1}"));
//        eventBusHelper.postEvent(new MqttMessageEvent("{\"msgType\":1,\"ipaddr\":\"192.168.2.8\",\"port\":1883,\"msgBody\":{\"statusRpt\":[{\"taskid\":77,\"status\":0}]}}"));
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
