package crispy_octo_moo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import crispy_octo_moo.dto.JsonObject;
import crispy_octo_moo.dto.LabelValueObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by yangboz on 9/24/15.
 */
@RestController
@RequestMapping("/v1/evCredit")
public class EVCreditController {


//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
//    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
//    public Map<String, Float> create(@RequestBody @Valid Map<String, Float> value) {
//        FixtureData.ElectricVehicleCredits.putAll(value);
//        return FixtureData.ElectricVehicleCredits;
//    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
    public JsonObject list() {
        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> lMap = new HashMap<String, String>();
        // Load the directory as a resource　for testing.
//        URL dir_url = ClassLoader.getSystemResource("ElectricVehicleCredits.json");//FIXME:missing resource files.
//        try {
//            //convert JSON string to Map
//            File jsonFile = new File(dir_url.toURI());
//            System.out.println("jsonFile:" + jsonFile);
//            lMap = mapper.readValue(jsonFile,
//                    new TypeReference<Map<String, String>>() {
//                    });
//            System.out.println("HashMap(ElectricVehicleCredits):" + lMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new JsonObject(FixtureData.ElectricVehicleCredits);
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the EVC key related value is successfully get or not.")
    public LabelValueObject get(@PathVariable("index") int index) {
        return FixtureData.ElectricVehicleCredits.get(index);
    }

    //
    @RequestMapping(value = "/{index}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  EVC item  is successfully updated or not.")
    public ResponseEntity<Boolean> update(@PathVariable("index") int index, @RequestBody @Valid LabelValueObject value) {
//		User find = this._userDao.findOne(id);
        FixtureData.ElectricVehicleCredits.add(index, value);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/{index}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the EVC item is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("index") int index) {
        FixtureData.ElectricVehicleCredits.remove(index);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
