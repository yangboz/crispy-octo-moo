package crispy_octo_moo.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.DealObject;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.LabelObject;
import crispy_octo_moo.dto.sqoot.SqootDealsObject;
import crispy_octo_moo.service.SqootDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangboz on 9/30/15.
 */
@RestController
@RequestMapping("/v1/deals")
public class SqootDealsController {

    @Autowired
    SqootDealService sqootDealService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of deal objects that is successfully get or not.")
    public JsonObject getDeals() {
        return new JsonObject(FixtureData.DealObjects);
    }

    @RequestMapping(value = "/by/{keywords}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a SqootDealsObject describing if the category keywords related value is successfully get or not.")
    public SqootDealsObject getDealsByKeywords(@PathVariable("keywords") String keywords) {
        return sqootDealService.getDeals(keywords);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of deal categories that is successfully get or not.")
    public JsonObject getCategories() {
        return new JsonObject(sqootDealService.getCategories());
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the SqootDeals item value is successfully get or not.")
    public DealObject getDeal(@PathVariable("index") int index) {
        return FixtureData.DealObjects.get(index);
    }
}
