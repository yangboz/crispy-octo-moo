package crispy_octo_moo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "snap415_user_profile")
public class Snap415UserProfile extends BaseEntity{


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

		
	///Extra
    private int income;
    private String taxfilingstatus;
    private int numberofChildren;

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
