package crispy_octo_moo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangboz on 9/24/15.
 */
@RestController
@RequestMapping("/v1/evc")
public class EVCController {


//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
//    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the user info is successfully created or not.")
//    public Map<String, String> create(@RequestBody @Valid Map<String, String>) {
//        return _userDao.save(user);
//    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of FB user info that is successfully get or not.")
    public Map<String, String> list() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> lMap = new HashMap<String, String>();
        // Load the directory as a resource　for testing.
        URL dir_url = ClassLoader.getSystemResource("ElectricVehicleCredit.json");//FIXME:missing resource files.
        try {
            //convert JSON string to Map
            File jsonFile = new File(dir_url.toURI());
            System.out.println("jsonFile:" + jsonFile);
            lMap = mapper.readValue(jsonFile,
                    new TypeReference<Map<String, String>>() {
                    });
            System.out.println("HashMap(ElectricVehicleCredit):" + lMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lMap;
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the user info id is successfully get or not.")
//    public Snap415UserProfile get(@PathVariable("id") String id) {
//        return this._userDao.findOne(id);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  user info is successfully updated or not.")
//    public Snap415UserProfile update(@PathVariable("id") String id, @RequestBody @Valid Snap415UserProfile user) {
////		User find = this._userDao.findOne(id);
//        user.setId(id);
//        return this._userDao.save(user);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the user info is successfully delete or not.")
//    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
//        this._userDao.delete(id);
//        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
//    }
}
