package crispy_octo_moo.extractor;

import crispy_octo_moo.domain.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;
import java.util.Calendar;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An extractor that extracts info from FB Post.
 */
public class PostExtractor {
    private static final Logger LOG = LoggerFactory.getLogger(PostExtractor.class);

    /**
     * Extracts events from given post and place the vents in given profile...
     *   input:  posts
     *   output: container
     * TODO: one post at a time?
     */
    private static void extract(Snap415UserPosts posts, ExtractedData container) {
        LOG.info(String.format("[extract(%1$d-posts)...", posts.getPosts().size()));
        for(Snap415FBPost fbPost : posts.getPosts()) {
            extract(fbPost, container);
        }
    }

    public static void extract(Snap415FBPost fbPost, ExtractedData container) {
        String story = fbPost.getStory();
        if(story != null)
        {
	        if (story.trim().length() > 0) {
	            String storyLowerCase = story.toLowerCase();
	            extractChild(fbPost, storyLowerCase, container);
	            extractElectricVehicle(fbPost, storyLowerCase, container);
	            extractMarriageStatus(fbPost, storyLowerCase, container);
	        }
        }
    }

    // marriage etc.
    //
    // honeymoon
    // congrats new wed!
    // wedding reception
    // wedded
    public static void extractMarriageStatus(Snap415FBPost fbPost, String fbPostStory, ExtractedData container) {
        boolean isMarried = false;
        isMarried = isMarried ||
          (
            (  (fbPostStory.indexOf("honeymoon") >= 0)
            || (fbPostStory.indexOf("wedding") >= 0)
            || (fbPostStory.indexOf("new wed") >= 0)
            || (fbPostStory.indexOf("wedding") >= 0)
            )
          );
        if (isMarried) {
            container._isMarried = isMarried;
        }
    }

    // Welcome MY NAME v2 to the world!
    // welcome X to the world
    // X was born in Y hospital at 8am. Both mom and baby are doing great.
    // X was born to the Y's family.
    // at X lb's and weights Y lbs.
    // Chrystal was born 
    // congrates on your new born! She is so cute/adorable/precious/beautiful/pretty!
    // he just looks like you
    private static void extractChild(Snap415FBPost fbPost, String fbPostStory, ExtractedData container) {
        LOG.info(String.format("[extractChild(%1$s)...", fbPost.getPostId()));
        boolean isChildBorn = false;
        isChildBorn = isChildBorn ||
          (
            (  (fbPostStory.indexOf("mom and baby") >= 0)
            || (fbPostStory.indexOf("baby and mom") >= 0)
            )
          && (fbPostStory.indexOf("doing") >= 0)
          && ( (fbPostStory.indexOf("fine") >= 0)
            || (fbPostStory.indexOf("great") >= 0)
          )
          );
        isChildBorn = isChildBorn ||
          (
            (  (fbPostStory.indexOf("cute") >= 0)
            || (fbPostStory.indexOf("adorable") >= 0)
            || (fbPostStory.indexOf("pretty") >= 0)
            || (fbPostStory.indexOf("beautiful") >= 0)
            )
          && (fbPostStory.indexOf("congrat") >= 0)
          );

        if (isChildBorn) {
            // TODO: getYear etc. is deprecated
            // TODO: using Post.CreatedTime as child' DOB is problematic at best
            // TODO: need to de-dup as different posts may refer to same child
            // TODO: need to find cases of twins, triplets, etc.
            LOG.info(String.format("  isChildBorn!"));
            Date postCreatedTime = fbPost.getCreatedTime();
            Snap415Child child = new Snap415Child(fbPost.getPostId(), postCreatedTime.getYear(), postCreatedTime.getMonth(), postCreatedTime.getDate());
            container._children.add(child);
        }
    }

    // love my first electric vehicle
    // bought|purchased my first electric vehicle
    // nissan leaf
    // toyota prius
    // chevy volt
    // tesla model T
    // tesla model S
    // byd e6
    // EV
    // Electric car
    private static void extractElectricVehicle(Snap415FBPost fbPost, String fbPostStory, ExtractedData container) {
        LOG.info(String.format("[extractElectricVehicle(%1$s)...", fbPost.getPostId()));
        boolean boughtEV = false;
        boughtEV = boughtEV ||
          (
              (
                (fbPostStory.indexOf("bought") >= 0)
              ||(fbPostStory.indexOf("buy") >= 0)
              ||(fbPostStory.indexOf("purchase") >= 0)
              ||(fbPostStory.indexOf("get my hands on") >= 0)
              ||(fbPostStory.indexOf("got my hands on") >= 0)
              )
            &&
              (
                (fbPostStory.indexOf("nissan leaf") >= 0)
              ||(fbPostStory.indexOf("toyota prius") >= 0)
              ||(fbPostStory.indexOf("chevy volt") >= 0)
              ||(fbPostStory.indexOf("byd e6d") >= 0)
              ||(fbPostStory.indexOf("tesla model t") >= 0)
              ||(fbPostStory.indexOf("tesla model s") >= 0)
              ||(fbPostStory.indexOf("tesla model") >= 0)
              ||((fbPostStory.indexOf("electric") >= 0) && (fbPostStory.indexOf("char") >= 0))
              ||(fbPostStory.indexOf("ev") >= 0)
              )
          );

        if (boughtEV) {
            LOG.info(String.format("  boughtEV!"));
            // TODO: getYear etc. is deprecated
            Date postCreatedTime = fbPost.getCreatedTime();
            Snap415ElectricVehicle electricVehicle = new Snap415ElectricVehicle(fbPost.getPostId(), postCreatedTime.getYear(), postCreatedTime.getMonth(), postCreatedTime.getDate());
          container._evPurchases.add(electricVehicle);
        }
    }
}
