package crispy_octo_moo.domain;
//The information retrieved from FB has the following scope:
//All the information retrieved from the above scope, except user_posts, will be mapped to FbUserProfile,
//the FbUserProfile also contains access token for checking if the token is still valid,

//The Snap415UserProfile is a superset that includes FbUserProfile and LiUserProfile.
// It also has extra information related to tax filing: including income, tax filing status,
// number of children (this attribute can possibly be inferred or retrieved from facebook,
// but for now, we rely on user input).

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user_profile")
public class Snap415UserProfile extends BaseEntity {


    private User fbUserProfile;
    private LiUserProfile liUserProfile;

    public User getFbUserProfile() {
        return fbUserProfile;
    }

    public void setFbUserProfile(User fbUserProfile) {
        this.fbUserProfile = fbUserProfile;
    }

    public LiUserProfile getLiUserProfile() {
        return liUserProfile;
    }

    public void setLiUserProfile(LiUserProfile liUserProfile) {
        this.liUserProfile = liUserProfile;
    }

    //Simplify basic information as required.
    private String simplyRelationshipStatus;
    private String simplyEducation;
    private String simplyBirthday;
    private String simplyWork;
    //Also allow user input,read and write
    private int rwIncome;
    private String rwTaxFilingStatus;
    private int rwNumberOfChildren;


    public String getSimplyRelationshipStatus() {
        return simplyRelationshipStatus;
    }

    public void setSimplyRelationshipStatus(String simplyRelationshipStatus) {
        this.simplyRelationshipStatus = simplyRelationshipStatus;
    }

    public String getSimplyEducation() {
        return simplyEducation;
    }

    public void setSimplyEducation(String simplyEducation) {
        this.simplyEducation = simplyEducation;
    }

    public String getSimplyBirthday() {
        return simplyBirthday;
    }

    public void setSimplyBirthday(String simplyBirthday) {
        this.simplyBirthday = simplyBirthday;
    }

    public String getSimplyWork() {
        return simplyWork;
    }

    public void setSimplyWork(String simplyWork) {
        this.simplyWork = simplyWork;
    }

    public int getRwIncome() {
        return rwIncome;
    }

    public void setRwIncome(int rwIncome) {
        this.rwIncome = rwIncome;
    }

    public String getRwTaxFilingStatus() {
        return rwTaxFilingStatus;
    }

    public void setRwTaxFilingStatus(String rwTaxFilingStatus) {
        this.rwTaxFilingStatus = rwTaxFilingStatus;
    }

    public int getRwNumberOfChildren() {
        return rwNumberOfChildren;
    }

    public void setRwNumberOfChildren(int rwNumberOfChildren) {
        this.rwNumberOfChildren = rwNumberOfChildren;
    }

    public String toString() {
        return "birthday:" + this.getSimplyBirthday() + ",work:" + this.getSimplyWork() + ",education:" + this.getSimplyEducation()
                + ",relationshipStatus:" + this.getSimplyRelationshipStatus() + ",fbUserProfile:" + this.getFbUserProfile();
    }
}
