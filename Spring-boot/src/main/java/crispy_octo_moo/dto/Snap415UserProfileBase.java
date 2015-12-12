package crispy_octo_moo.dto;

/**
 * Created by yangboz on 10/8/15.
 */
public class Snap415UserProfileBase {

    //Simplify basic information as required.
    private String simplyRelationshipStatus;
    private Object simplyEducation;
    private String simplyBirthday;
    private Object simplyWork;
    //Also allow user input,read and write
    private int rwIncome;
    private String rwTaxFilingStatus;
    private int rwNumberOfChildren;

    public Snap415UserProfileBase() {
    }

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

    @Override
    public String toString() {
        return "Snap415UserProfileBase{" +
                "simplyRelationshipStatus='" + simplyRelationshipStatus + '\'' +
                ", simplyEducation=" + simplyEducation +
                ", simplyBirthday='" + simplyBirthday + '\'' +
                ", simplyWork=" + simplyWork +
                ", rwIncome=" + rwIncome +
                ", rwTaxFilingStatus='" + rwTaxFilingStatus + '\'' +
                ", rwNumberOfChildren=" + rwNumberOfChildren +
                '}';
    }
}
