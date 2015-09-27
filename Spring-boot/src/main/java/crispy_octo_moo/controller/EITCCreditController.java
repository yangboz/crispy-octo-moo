package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.EITCCreditObject;
import crispy_octo_moo.dto.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangboz on 9/27/15.
 */
@RestController
@RequestMapping("/v1/eitcCredit")
public class EITCCreditController {

    private final Logger LOG = LoggerFactory.getLogger(EITCCreditController.class);

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
}
