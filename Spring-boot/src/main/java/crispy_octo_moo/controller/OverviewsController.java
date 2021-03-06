package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangboz on 9/30/15.
 */
@RestController
@RequestMapping("/v1/overviews")
public class OverviewsController {
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of overview objects that is successfully get or not.")
    public JsonObject list() {
        return new JsonObject(FixtureData.OverviewObjects);
    }
}
