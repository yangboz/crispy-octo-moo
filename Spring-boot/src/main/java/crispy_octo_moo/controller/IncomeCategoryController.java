package crispy_octo_moo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.LabelObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangboz on 9/27/15.
 */
@RestController
@RequestMapping("/v1/incomeCategory")
public class IncomeCategoryController {

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of IncomeCategory that is successfully get or not.")
    public JsonObject list() {
        return new JsonObject(FixtureData.IncomeCategory);
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the IncomeCategory related value is successfully get or not.")
    public LabelObject get(@PathVariable("index") int index) {
        return FixtureData.IncomeCategory.get(index);
    }

    //
    @RequestMapping(value = "/{index}/{value}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  IncomeCategory item  is successfully updated or not.")
    public ResponseEntity<Boolean> update(@PathVariable("index") int index, @PathVariable("value") String value) {
//		User find = this._userDao.findOne(id);
        FixtureData.IncomeCategory.set(index, new LabelObject(value));
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/{index}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the IncomeCategory item is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("index") int index) {
        FixtureData.IncomeCategory.remove(index);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }


}
