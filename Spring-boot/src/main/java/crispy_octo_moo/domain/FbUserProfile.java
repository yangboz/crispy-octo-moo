package crispy_octo_moo.domain;


import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_fb_user_profile")
public class FbUserProfile extends User {

    public FbUserProfile(String id, String name, String firstName, String lastName, String gender, Locale locale) {
        super(id, name, firstName, lastName, gender, locale);
    }

    @Id
    private String id;

    private String name;
   	private String sex;
   	private String relationshipstatus;
   	private List<EducationExperience> education;
   	private String workstatus;
   	
   	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

	public List<EducationExperience> getEducation() {
		return education;
	}

	public void setEducation(List<EducationExperience> education) {
		this.education = education;
	}

	public String getWorkstatus() {
		return workstatus;
	}

	public void setWorkstatus(String workstatus) {
		this.workstatus = workstatus;
	}


}
