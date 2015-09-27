package crispy_octo_moo.dto;

/**
 * Created by yangboz on 9/27/15.
 */
public class EITCCreditObject {
    public String relationshipStatus;
    public int numberOfChildren;
    public int income;

    public EITCCreditObject(String relationshipStatus, int numberOfChildren, int income) {
        this.relationshipStatus = relationshipStatus;
        this.numberOfChildren = numberOfChildren;
        this.income = income;
    }

    //Introducing the dummy constructor
    public EITCCreditObject() {
    }

}
