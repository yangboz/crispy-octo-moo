package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//The Snap415UserTaxEvents is the result from analyzing and filtering Snap415UserPosts.
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_user_tax_events")
public class Snap415UserTaxEvents {

    @Id
    private String id;

    //This is the user id for linking a user's profile to a user entity
    private String snap415ID;

    private ArrayList<Snap415TaxEvent> taxEvents;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getSnap415ID() {
		return snap415ID;
	}

	public void setSnap415ID(String snap415id) {
		snap415ID = snap415id;
	}

	public ArrayList<Snap415TaxEvent> getTaxEvents() {
		return taxEvents;
	}

	public void setTaxEvents(ArrayList<Snap415TaxEvent> taxEvents) {
		this.taxEvents = taxEvents;
	}
}
