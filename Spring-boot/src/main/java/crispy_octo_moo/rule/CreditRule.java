package crispy_octo_moo.rule;

/**
 * Defines a Credit Rule with
 *   RuleId
 *   Credit Amount
 *   Description
 */
public class CreditRule {
    public String _ruleId; // unique between rules
    public String _description;
    public int _incomeThreshold = -1;// means "no threshold"
    public int _minKidCount = -1;// means "no threshold"
    public int _creditAmount;

    public CreditRule(String ruleId, int incomeThreshold, int minKidCount, int creditAmount) {
        _ruleId = ruleId;
        _incomeThreshold = incomeThreshold;
        _minKidCount = minKidCount;
        _creditAmount = creditAmount;
        _description = null;
    }

    public CreditRule(String ruleId, int incomeThreshold, int minKidCount, int creditAmount, String description) {
        _ruleId = ruleId;
        _incomeThreshold = incomeThreshold;
        _minKidCount = minKidCount;
        _creditAmount = creditAmount;
        _description = description;
    }

    public CreditRule(String ruleId, int incomeThreshold, int creditAmount) {
        _ruleId = ruleId;
        _incomeThreshold = incomeThreshold;
        _creditAmount = creditAmount;
        _description = null;
    }

    public CreditRule(String ruleId, int incomeThreshold, int creditAmount, String description) {
        _ruleId = ruleId;
        _incomeThreshold = incomeThreshold;
        _creditAmount = creditAmount;
        _description = description;
    }

    public CreditRule(String ruleId, int creditAmount) {
        _ruleId = ruleId;
        _creditAmount = creditAmount;
        _description = null;
    }

    public CreditRule(String ruleId, int creditAmount, String description) {
        _ruleId = ruleId;
        _creditAmount = creditAmount;
        _description = description;
    }

    // TODO: override
    public int hashCode() {
        return _ruleId.hashCode();
    }

    public String toString() {
        return String.format("{ruleId=%1$s, _incomeThreshold=%4$d, _minKidCount=%5$d, creditAmount=%2$d, description='%3$s'}",
            _ruleId, _creditAmount, _description, _incomeThreshold, _minKidCount);
    }
}
