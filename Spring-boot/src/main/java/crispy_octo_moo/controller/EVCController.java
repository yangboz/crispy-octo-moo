package crispy_octo_moo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import crispy_octo_moo.consts.FixtureData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangboz on 9/24/15.
 */
@RestController
@RequestMapping("/v1/evc")
public class EVCController {


//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
//    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
//    public Map<String, Float> create(@RequestBody @Valid Map<String, Float> value) {
//        FixtureData.ElectricVehicleCredit.putAll(value);
//        return FixtureData.ElectricVehicleCredit;
//    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
    public Map<String, Float> list() {
        ObjectMapper mapper = new ObjectMapper();
//        Map<String, String> lMap = new HashMap<String, String>();
        // Load the directory as a resource　for testing.
//        URL dir_url = ClassLoader.getSystemResource("ElectricVehicleCredit.json");//FIXME:missing resource files.
//        try {
//            //convert JSON string to Map
//            File jsonFile = new File(dir_url.toURI());
//            System.out.println("jsonFile:" + jsonFile);
//            lMap = mapper.readValue(jsonFile,
//                    new TypeReference<Map<String, String>>() {
//                    });
//            System.out.println("HashMap(ElectricVehicleCredit):" + lMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return FixtureData.ElectricVehicleCredit;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the EVC key related value is successfully get or not.")
    public Float get(@PathVariable("key") String key) {
        return FixtureData.ElectricVehicleCredit.get(key);
    }

    //
    @RequestMapping(value = "/{key}/{value}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  EVC item  is successfully updated or not.")
    public ResponseEntity<Boolean> update(@PathVariable("key") String key, @PathVariable("value") Float value) {
//		User find = this._userDao.findOne(id);
        FixtureData.ElectricVehicleCredit.put(key, value);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the EVC item is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("key") String key) {
        FixtureData.ElectricVehicleCredit.remove(key);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }
}
