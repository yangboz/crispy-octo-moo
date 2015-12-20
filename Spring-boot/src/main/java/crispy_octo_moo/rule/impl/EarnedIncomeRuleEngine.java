package crispy_octo_moo.rule.impl;

import crispy_octo_moo.domain.Snap415FBPost;
import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.*;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.dto.*;
import crispy_octo_moo.rule.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * by wyu on 12/15/2015.
 */
public class EarnedIncomeRuleEngine implements RuleEngine {
    private final Logger LOG = LoggerFactory.getLogger(EarnedIncomeRuleEngine.class);

    public void apply(Snap415UserProfile profile, Snap415UserTaxEvents taxEvents) {
        boolean isMarried = profile.getIsMarried();
        int childCount = profile.getChildCount();
        int income = profile.getIncomeAmount();
        if(isMarried) {
            // most-credit rule *first* !!!
            CreditRule[] rules = new CreditRule[]{
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_MARRIED_3KID_LT53267D),
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_MARRIED_2KID_LT49974D),
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_MARRIED_1KID_LT44651D),
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_MARRIED_0KID_LT20330D) 
            };
            for (CreditRule rule : rules) {
                if(childCount >= rule._minKidCount) {
                    if(income < rule._incomeThreshold) {
                        Snap415TaxEvent taxEvent = new Snap415TaxEvent();
                        taxEvent.setCreditRule(rule);
                        taxEvents.addTaxEvent(taxEvent);
                    }
                }
                break; // don't apply the lower-credit rules - mutually exclusive
            }
        } else {
            // most-credit rule *first* !!!
            CreditRule[] rules = new CreditRule[]{
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_SINGLE_3KID_LT47747D ),
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_SINGLE_2KID_LT44454D ),
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_SINGLE_1KID_LT39131D ),
                CreditRuleFactory.RuleId2CreditRules2015.get(CreditRuleFactory.RuleId.EarnedIncome_SINGLE_0KID_LT14820D )
            };
            for (CreditRule rule : rules) {
                if(childCount >= rule._minKidCount) {
                    if(income < rule._incomeThreshold) {
                        Snap415TaxEvent taxEvent = new Snap415TaxEvent();
                        taxEvent.setCreditRule(rule);
                        taxEvents.addTaxEvent(taxEvent);
                    }
                }
                break; // don't apply the lower-credit rules - mutually exclusive
            }
        }

        LOG.info(String.format("-apply(Snap415UserProfile) got %1$d credits (some might have already existed)", taxEvents.getTaxEvents().size()));
    }

    // TODO
    public void apply(Snap415UserProfile profile, Snap415FBPost post, Snap415UserTaxEvents taxEvents) {
        LOG.info(String.format("***TODO*** implement -apply(Snap415UserProfile, Snap415FBPost, Snap415UserTaxEvents)"));
    }
}
