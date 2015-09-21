package crispy_octo_moo.domain;
//The information retrieved from FB has the following scope:
//All the information retrieved from the above scope, except user_posts, will be mapped to FbUserProfile,
//the FbUserProfile also contains access token for checking if the token is still valid,

//The Snap415UserProfile is a superset that includes FbUserProfile and LiUserProfile.
// It also has extra information related to tax filing: including income, tax filing status,
// number of children (this attribute can possibly be inferred or retrieved from facebook,
// but for now, we rely on user input).

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "com_user_profile")
public class Snap415UserProfile extends BaseEntity {


    private FbUserProfile fbUserProfile;
    private LiUserProfile liUserProfile;

    public FbUserProfile getFbUserProfile() {
        return fbUserProfile;
    }

    public void setFbUserProfile(FbUserProfile fbUserProfile) {
        this.fbUserProfile = fbUserProfile;
    }

    public LiUserProfile getLiUserProfile() {
        return liUserProfile;
    }

    public void setLiUserProfile(LiUserProfile liUserProfile) {
        this.liUserProfile = liUserProfile;
    }
    // @Id
    // private String id;

    // This is the user id for linking a user's profile to a user entity
    private String userid;

    private String name;
    private String sex;
    private String relationshipstatus;
    private String education;
    private String workstatus;
    private int income;
    private String taxfilingstatus;
    private int numberofChildren;

    // public String getId() {
    // return id;
    // }
    // public void setId(String id) {
    // this.id = id;
    // }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelationshipstatus() {
        return relationshipstatus;
    }

    public void setRelationshipstatus(String relationshipstatus) {
        this.relationshipstatus = relationshipstatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getTaxfilingstatus() {
        return taxfilingstatus;
    }

    public void setTaxfilingstatus(String taxfilingstatus) {
        this.taxfilingstatus = taxfilingstatus;
    }

    public int getNumberofChildren() {
        return numberofChildren;
    }

    public void setNumberofChildren(int numberofChildren) {
        this.numberofChildren = numberofChildren;
    }

}
