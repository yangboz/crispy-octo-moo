package crispy_octo_moo.dto;

/**
 * Created by yangboz on 9/27/15.
 */
public class EITCCreditObject {
    private String relationshipStatus;
    private int numberOfChildren;
    private int income;
    private int credit;

    public EITCCreditObject(String relationshipStatus, int numberOfChildren, int income) {
        this.relationshipStatus = relationshipStatus;
        this.numberOfChildren = numberOfChildren;
        this.income = income;
        this.credit = 0;
    }

    //Introducing the dummy constructor
    public EITCCreditObject() {
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "EITCCreditObject{" +
                "relationshipStatus='" + relationshipStatus + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", income=" + income +
                ", credit=" + credit +
                '}';
    }
}
