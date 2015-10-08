package crispy_octo_moo.dto;

/**
 * Created by yangboz on 10/8/15.
 */
public class Snap415UserProfileBase {

    public Snap415UserProfileBase() {
    }

    //Simplify basic information as required.
    private String simplyRelationshipStatus;
    private Object simplyEducation;
    private String simplyBirthday;
    private Object simplyWork;
    //Also allow user input,read and write
    private int rwIncome;
    private String rwTaxFilingStatus;
    private int rwNumberOfChildren;

    public String getSimplyRelationshipStatus() {
        return simplyRelationshipStatus;
    }

    public void setSimplyRelationshipStatus(String simplyRelationshipStatus) {
        this.simplyRelationshipStatus = simplyRelationshipStatus;
    }

    public Object getSimplyEducation() {
        return simplyEducation;
    }

    public void setSimplyEducation(Object simplyEducation) {
        this.simplyEducation = simplyEducation;
    }

    public String getSimplyBirthday() {
        return simplyBirthday;
    }

    public void setSimplyBirthday(String simplyBirthday) {
        this.simplyBirthday = simplyBirthday;
    }

    public Object getSimplyWork() {
        return simplyWork;
    }

    public void setSimplyWork(Object simplyWork) {
        this.simplyWork = simplyWork;
    }

    public int getRwIncome() {
        return rwIncome;
    }

    public void setRwIncome(int rwIncome) {
        this.rwIncome = rwIncome;
    }

    public String getRwTaxFilingStatus() {
        return rwTaxFilingStatus;
    }

    public void setRwTaxFilingStatus(String rwTaxFilingStatus) {
        this.rwTaxFilingStatus = rwTaxFilingStatus;
    }

    public int getRwNumberOfChildren() {
        return rwNumberOfChildren;
    }

    public void setRwNumberOfChildren(int rwNumberOfChildren) {
        this.rwNumberOfChildren = rwNumberOfChildren;
    }

    public String toString() {
        return "birthday:" + this.getSimplyBirthday() + ",work:" + this.getSimplyWork() + ",education:" + this.getSimplyEducation()
                + ",relationshipStatus:" + this.getSimplyRelationshipStatus();
    }


}
