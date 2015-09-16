package crispy_octo_moo.domain;

import org.springframework.data.annotation.Id;


public class UserProfile {

	@Id
	private String id;
	
	//This is the user id for linking a user's profile to a user entity
	private String userid;
	
	private String name;
	private String sex;
	private String relationshipstatus;
	private String education;
	private String workstatus;
	private int income;
	private String taxfilingstatus;
	private int numberofChildren;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}
