
package crispy_octo_moo.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Defines
 *   a set of CreditRule's
 *   a set of RuleId's
 */
public class CreditRuleFactory {
    public static final Map<Integer, Set<CreditRule>> CreditRules = new HashMap<Integer, Set<CreditRule>>();
    public static final Set<CreditRule> CreditRules2015; // TODO: Set has no get() API!!! hence need Map below
    public static final Map<String, CreditRule> RuleId2CreditRules2015 = new HashMap<String, CreditRule>();

    static {
        CreditRules2015 = new HashSet<CreditRule>();
        // order doesn't really matter
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_SINGLE_3KID_LT47747D , 47747, 3, 6242, "Single, Income less than $47,747, 3 or more kids" ));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_SINGLE_2KID_LT44454D , 44454, 2, 5548, "Single, Income less than $44,454, 2 kids"         ));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_SINGLE_1KID_LT39131D , 39131, 1, 3359, "Single, Income less than $39,131, 1 kid"          ));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_SINGLE_0KID_LT14820D , 14820, 0,  503, "Single, Income less than $14,820, no kids"        ));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_MARRIED_3KID_LT53267D, 53267, 3, 6242, "Married, Income less than $53,267, 3 or more kids"));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_MARRIED_2KID_LT49974D, 49974, 2, 5548, "Married, Income less than $49,974, 2 kids"        ));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_MARRIED_1KID_LT44651D, 44651, 1, 3359, "Married, Income less than $44,651, 1 kid"         ));
        CreditRules2015.add(new CreditRule(RuleId.EarnedIncome_MARRIED_0KID_LT20330D, 20330, 0,  503, "Married, Income less than $20,330, no kids"       ));
        CreditRules2015.add(new CreditRule(RuleId.DependentCare_1KIDXAGE10          ,         3000, "Max child care credit $3000 for 1 kid under 10"   ));
        CreditRules2015.add(new CreditRule(RuleId.DependentCare_2KIDXAGE10          ,         6000, "Max child care credit $6000 for 2+ kids under 10" ));

        for(CreditRule cr : CreditRules2015) {
            RuleId2CreditRules2015.put(cr._ruleId, cr);
        }

        CreditRules.put(2015, CreditRules2015);
    }

    // defines keys that can be used to access CreditRules
    public final class RuleId {
        public static final String EarnedIncome_SINGLE_0KID_LT14820D  = "EarnedIncome_SINGLE_0KID_LT14820D";    // TODO: "KID" has to be 10years or younger?
        public static final String EarnedIncome_SINGLE_1KID_LT39131D  = "EarnedIncome_SINGLE_1KID_LT39131D";
        public static final String EarnedIncome_SINGLE_2KID_LT44454D  = "EarnedIncome_SINGLE_2KID_LT44454D";
        public static final String EarnedIncome_SINGLE_3KID_LT47747D  = "EarnedIncome_SINGLE_3KID_LT47747D";
        public static final String EarnedIncome_MARRIED_0KID_LT20330D = "EarnedIncome_MARRIED_0KID_LT20330D";
        public static final String EarnedIncome_MARRIED_1KID_LT44651D = "EarnedIncome_MARRIED_1KID_LT44651D";
        public static final String EarnedIncome_MARRIED_2KID_LT49974D = "EarnedIncome_MARRIED_2KID_LT49974D";
        public static final String EarnedIncome_MARRIED_3KID_LT53267D = "EarnedIncome_MARRIED_3KID_LT53267D";
        public static final String DependentCare_1KIDXAGE10           = "DependentCare_1KIDXAGE10";
        public static final String DependentCare_2KIDXAGE10           = "DependentCare_2KIDXAGE10";
    }
}
