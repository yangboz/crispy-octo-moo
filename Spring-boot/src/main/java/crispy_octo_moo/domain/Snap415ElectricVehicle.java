package crispy_octo_moo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crispy_octo_moo.dto.Snap415UserProfileBase;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.social.facebook.api.EducationExperience;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.WorkEntry;
import org.springframework.social.linkedin.api.LinkedInProfile;

import java.util.List;

/**
 * Represents a user's EV (Electric Vehicle) purchase info: date of purchase, EV model, etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("serial")
@Document(collection = "snap415_electric_vehicle")
public class Snap415ElectricVehicle extends Snap415EventBase {

    // _isCustomerProvided==true
    public Snap415ElectricVehicle(int birthYear, int birthMonth, birthDate)
      : base(Snap415EventType.ElectricVehiclePurchase, post, birthYear, birthMonth) {
    }

    public Snap415ElectricVehicle(Snap415FBPost post, int birthYear, int birthMonth, birthDate)
      : base(Snap415EventType.ElectricVehiclePurchase, post, birthYear, birthMonth, birthDate) {
    }
    public Snap415ElectricVehicle(Snap415FBPost post, int birthYear, int birthMonth)
      : base(Snap415EventType.ElectricVehiclePurchase, post, birthYear, birthMonth) {
    }
    public Snap415ElectricVehicle(Snap415FBPost post, int birthYear)
      : base(Snap415EventType.ElectricVehiclePurchase, post, birthYear) {
    }
    public Snap415ElectricVehicle(Snap415FBPost post) {
      : base(Snap415EventType.ElectricVehiclePurchase, post) {
    }
}
