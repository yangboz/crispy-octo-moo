package org.springframework.social.facebook.api.impl;

import org.springframework.social.facebook.api.User;

/**
 * Created by yangboz on 12/8/15.
 *
 * @see: http://stackoverflow.com/questions/34128050/how-do-i-report-a-severe-bug-to-the-spring-social-facebook-team-login-fails-aft
 */
public class ComFacebookTemplate extends FacebookTemplate {


    // This doesnot contain: video_upload_limits which was causing the issue
    static final String[] MY_PROFILE_FIELDS = {
            "id", "about", "age_range", "address", "bio", "birthday", "context", "cover", "currency", "devices", "education", "email",
            "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type",
            "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format",
            "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other",
            "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "viewer_can_send_gift",
            "website", "work"
    };

    public ComFacebookTemplate(String accessToken) {
        super(accessToken);

    }

//    @PostConstruct
//    private void init() {
//        // hack for the login of facebook.
//        try {
//            String[] fieldsToMap = {
//                    "id", "about", "age_range", "address", "bio", "birthday", "context", "cover", "currency", "devices", "education", "email",
//                    "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type",
//                    "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format",
//                    "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other",
//                    "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "viewer_can_send_gift",
//                    "website", "work"
//            };
//
//            Field field = Class.forName("org.springframework.social.facebook.api.UserOperations").
//                    getDeclaredField("PROFILE_FIELDS");
//            field.setAccessible(true);
//
//            Field modifiers = field.getClass().getDeclaredField("modifiers");
//            modifiers.setAccessible(true);
//            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//            field.set(null, fieldsToMap);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    //New method, not in the original interface
    public User getUserProfile() {
        return this.fetchObject("me", User.class, MY_PROFILE_FIELDS);
    }
}
