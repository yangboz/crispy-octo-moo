package crispy_octo_moo.rule;

import crispy_octo_moo.rule.impl.*;
import crispy_octo_moo.domain.FbUserProfile;
import crispy_octo_moo.domain.Snap415FBPost;
import crispy_octo_moo.domain.Snap415UserProfile;
import crispy_octo_moo.domain.*;
import crispy_octo_moo.dto.Snap415Token;
import crispy_octo_moo.dto.*;
import java.util.*;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * by wyu on 12/15/2015.
 */
public final class RuleEngineFactory {
    private static final Logger LOG = LoggerFactory.getLogger(RuleEngineFactory.class);

    private static final RuleEngine[] _ruleEngines = new RuleEngine[] {
        new EarnedIncomeRuleEngine()
    };

    public static void applyRules(Snap415UserProfile profile, Snap415UserTaxEvents taxEvents) {
        for(RuleEngine ruleEngine : _ruleEngines) {
            ruleEngine.apply(profile, taxEvents);
        }

        LOG.info(String.format("applyRules: %1$d, got %2$d credit events (some could have been there)", _ruleEngines.length, taxEvents.getTaxEvents().size()));
    }
}
