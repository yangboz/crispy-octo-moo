package crispy_octo_moo.domain;


import java.util.Locale;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@SuppressWarnings("serial")
@Document(collection="com_fb_user")
public class FbUser extends User {
	
    public FbUser(String id, String name, String firstName, String lastName, String gender, Locale locale) {
		super(id, name, firstName, lastName, gender, locale);
	}
    
    @Id
    private String id;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
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
