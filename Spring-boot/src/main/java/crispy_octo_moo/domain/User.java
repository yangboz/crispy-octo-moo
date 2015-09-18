package crispy_octo_moo.domain;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;
	private String name;
	
	// Indicate if a use has a valid facebook account, the default is true, as facebook login is mandatory 
	private Boolean FacebookAccountExist = true;
	// Indicate if a use has a valid linkedin account
	private Boolean LinkedInAccountExist = false;
	
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
	public Boolean getFacebookAccountExist() {
		return FacebookAccountExist;
	}
	public void setFacebookAccountExist(Boolean facebookAccountExist) {
		FacebookAccountExist = facebookAccountExist;
	}
	public Boolean getLinkedInAccountExist() {
		return LinkedInAccountExist;
	}
	public void setLinkedInAccountExist(Boolean linkedInAccountExist) {
		LinkedInAccountExist = linkedInAccountExist;
	}

}
