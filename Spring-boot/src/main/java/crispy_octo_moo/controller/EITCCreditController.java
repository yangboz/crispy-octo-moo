package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.EITCCreditObject;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.service.EITCCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

/**
 * Created by yangboz on 9/27/15.
 */
@RestController
@RequestMapping("/v1/eitcCredit")
public class EITCCreditController {

    private final Logger LOG = LoggerFactory.getLogger(EITCCreditController.class);

    @Autowired
    private EITCCreditService eitcCreditService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the EITCCredit info is successfully created or not.")
    public JsonObject create(@RequestBody @Valid EITCCreditObject value) {
        //
//        List<Integer> incomeFixture = new ArrayList<Integer>(20330, 44651, 49974, 53267);
//        List<Integer> numberofChildrenThresholds = new ArrayList<Integer>(0, 1, 2, 3);
//        List<String> creditFixture = new ArrayList<String>("$503", "$3359", "$5548", "$6242", "unknown");
        String eitccreditdetail = "EITC Credit:";
//        return {
//                get: function ($relationship_status, $numberofChildren, $income) {
        if (value.relationshipStatus == "Married") {//"Married"
            for (int i = 0; i < FixtureData.numberofChildrenThresholds.size(); i++) {
                if (value.income < FixtureData.incomeFixture.get(i)) {
                    eitccreditdetail = "EITC Credit:" + FixtureData.creditFixture.get(i);
                } else {
                    eitccreditdetail = "EITC Credit: unknown";
                }
            }
        } else {
            eitccreditdetail = "EITC Credit: unknown";
        }

        LOG.info("eitccreditdetail:" + eitccreditdetail);

        return new JsonObject(eitccreditdetail);
    }

    @RequestMapping(value = "/{snap415Id}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the snap415Id related value is successfully get or not.")
    public JsonObject get(@PathVariable("snap415Id") String snap415Id) {
        return new JsonObject(eitcCreditService.getEITCCredit(snap415Id));
    }
}
