package crispy_octo_moo.domain;
//The information retrieved from FB has the following scope:
//All the information retrieved from the above scope, except user_posts, will be mapped to FbUserProfile,
//the FbUserProfile also contains access token for checking if the token is still valid,

//The Snap415UserProfile is a superset that includes FbUserProfile and LiUserProfile.
// It also has extra information related to tax filing: including income, tax filing status,
// number of children (this attribute can possibly be inferred or retrieved from facebook,
// but for now, we rely on user input).

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crispy_octo_moo.dto.ExtractedTypedMap;
import crispy_octo_moo.dto.Snap415UserProfileBase;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.User;
import org.springframework.social.linkedin.api.LinkedInProfile;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user_profile")
public class Snap415UserProfile extends BaseEntity {


    // This is the App's user ID to associate profile, posts, tax events, deals together
    // Currently, this ID is set the same as the FB user ID
    private String snap415ID;
    // Snap415UserProfile should contain *derived* info from *raw* data such as FB Profile
    // *derived* info includes (eg)
    //      Marriage Status
    //      Income
    //      Child list
    private boolean _isMarried = false;
    private int _incomeAmount = 12345; // TODO: needs to be set by user in app...
    // TODO: this isn't the perfect place to put child and EV purchase info
    private List<Snap415Child> _children = new ArrayList<Snap415Child>();
    private int _childCount = 0;
    private List<Snap415ElectricVehicle> _evPurchases = new ArrayList<Snap415ElectricVehicle>();
    private User fbUserProfile;
    private LinkedInProfile liUserProfile;
    private Snap415UserProfileBase profileBase = new Snap415UserProfileBase();
    //@example: http://stackoverflow.com/questions/3127189/java-classes-with-dynamic-fields/3127203#3127203
    private ExtractedTypedMap metaData = new ExtractedTypedMap();
//    String expected = "Hallo";
//    //    assertEquals(expected, value);
//    List<String> list = new ArrayList<String>();
//    metaData.put(ExtractedTypedMap.KEY1,expected;
//    //Extracted metadata field.
//
//    String value = metaData.get(ExtractedTypedMap.KEY1); // Look Ma, no cast!
//    metaData.put(ExtractedTypedMap.KEY2,list);
//    List<String> valueList = metaData.get(ExtractedTypedMap.KEY2); // Even with generics
////    assertEquals(list, valueList);

    public Snap415UserProfile() {
    }

    public int getIncomeAmount() {
        return _incomeAmount;
    }

    public boolean getIsMarried() {
        return _isMarried;
    }

    public void setIsMarried(boolean isMarried) {
        _isMarried = isMarried;
    }

    public int getChildCount() {
        return _childCount;
    }

    public void addChild(Snap415Child child) {
        _children.add(child);
    }

    public void addElectricVehicle(Snap415ElectricVehicle evPurchase) {
        _evPurchases.add(evPurchase);
    }

    public User getFbUserProfile() {
        return fbUserProfile;
    }

    public void setFbUserProfile(User fbUserProfile) {
        this.fbUserProfile = fbUserProfile;
    }

    public LinkedInProfile getLiUserProfile() {
        return liUserProfile;
    }

    public void setLiUserProfile(LinkedInProfile liUserProfile) {
        this.liUserProfile = liUserProfile;
    }

//    //Simplify basic information as required.
//    private String simplyRelationshipStatus;
//    private Object simplyEducation;
//    private String simplyBirthday;
//    private Object simplyWork;
//    //Also allow user input,read and write
//    private int rwIncome;
//    private String rwTaxFilingStatus;
//    private int rwNumberOfChildren;
//
//
//    public String getSimplyRelationshipStatus() {
//        return simplyRelationshipStatus;
//    }
//
//    public void setSimplyRelationshipStatus(String simplyRelationshipStatus) {
//        this.simplyRelationshipStatus = simplyRelationshipStatus;
//    }
//
//    public Object getSimplyEducation() {
//        return simplyEducation;
//    }
//
//    public void setSimplyEducation(Object simplyEducation) {
//        this.simplyEducation = simplyEducation;
//    }
//
//    public String getSimplyBirthday() {
//        return simplyBirthday;
//    }
//
//    public void setSimplyBirthday(String simplyBirthday) {
//        this.simplyBirthday = simplyBirthday;
//    }
//
//    public Object getSimplyWork() {
//        return simplyWork;
//    }
//
//    public void setSimplyWork(Object simplyWork) {
//        this.simplyWork = simplyWork;
//    }
//
//    public int getRwIncome() {
//        return rwIncome;
//    }
//
//    public void setRwIncome(int rwIncome) {
//        this.rwIncome = rwIncome;
//    }
//
//    public String getRwTaxFilingStatus() {
//        return rwTaxFilingStatus;
//    }
//
//    public void setRwTaxFilingStatus(String rwTaxFilingStatus) {
//        this.rwTaxFilingStatus = rwTaxFilingStatus;
//    }
//
//    public int getRwNumberOfChildren() {
//        return rwNumberOfChildren;
//    }
//
//    public void setRwNumberOfChildren(int rwNumberOfChildren) {
//        this.rwNumberOfChildren = rwNumberOfChildren;
//    }

    @Override
    public String toString() {
        return "Snap415UserProfile{" +
                "snap415ID='" + snap415ID + '\'' +
                ", _isMarried=" + _isMarried +
                ", _incomeAmount=" + _incomeAmount +
                ", _children=" + _children +
                ", _childCount=" + _childCount +
                ", _evPurchases=" + _evPurchases +
                ", fbUserProfile=" + fbUserProfile +
                ", liUserProfile=" + liUserProfile +
                ", profileBase=" + profileBase +
                ", metaData=" + metaData +
                '}';
    }

    public String getSnap415ID() {
        return snap415ID;
    }

    public void setSnap415ID(String snap415id) {
        this.snap415ID = snap415id;
    }

    public Snap415UserProfileBase getProfileBase() {
        return profileBase;
    }

    public void setProfileBase(Snap415UserProfileBase profileBase) {
        this.profileBase = profileBase;
    }

    public ExtractedTypedMap getMetaData() {
        return metaData;
    }

    public void setMetaData(ExtractedTypedMap metaData) {
        this.metaData = metaData;
    }
}
