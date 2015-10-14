package crispy_octo_moo.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.util.concurrent.FutureCallback;

import crispy_octo_moo.configs.SocialApiConfig;
import crispy_octo_moo.dto.sqoot.*;
import crispy_octo_moo.service.SqootDealService;
import crispy_octo_moo.utils.LoggingRequestInterceptor;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.coyote.Request;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * Created by yangboz on 9/30/15.
 */
@Service
public class SqootDealServiceImpl implements SqootDealService {

    private final Logger LOG = LoggerFactory.getLogger(SqootDealServiceImpl.class);

    //    @Value("${sqoot.snap415.apiKey}")
    private String sqootApiKey = "ucx07k";//FIXME:it should comes from property files;

    //Category from tax events key-words.
    @Override
    public SqootDealsObject getDeals(String category) {
//        String urlStr = "https://api.sqoot.com/v2/deals?api_key=" + sqootApiKey + "&category_slugs=" + category;//Invalid API url.
        String urlStr = "http://api.sqoot.com/v2/deals?api_key=" + sqootApiKey;
        LOG.info("SqootDealsObject url:" + urlStr);
        RestTemplate restTemplate = getRestTemplate();
//        restTemplate.
        Map params = new HashMap();
        params.put("category_slugs", category);
        params.put("api_key", sqootApiKey);
//        params.put("location",locationString);
        params.put("radius", "10");
        String respString = restTemplate.getForObject(urlStr, String.class, params);
//        LOG.info("respString:" + respString);
//        JSONObject jsonObject = new JSONObject(respString);
//        LOG.info("jsonObject:" + jsonObject.toString());
//        JSONArray dealsJsonArray = jsonObject.getJSONArray("deals");
//        LOG.info("dealsJsonArray:" + dealsJsonArray.toString());
        //JSON to JavaBean
        ObjectMapper objectMapper = new ObjectMapper();
        //ObjectMapper configures..
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //Single quote
        objectMapper
                .configure(
                        com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                        true);
        //
        JsonNode node = null;
        SqootDealsObject sqootDealsObject = new SqootDealsObject();
        try {
            node = objectMapper.readTree(respString);
//            LOG.info("objectMapper.readTree:" + node.toString());
            JsonNode queryNode = node.get("query");
            LOG.info("objectMapper.readTree:queryNode:" + queryNode);
            LOG.info("queryNode.total:" + queryNode.get("total").toString());
            SqootQuery sqootQuery = null;
            try {
                sqootQuery = objectMapper.readValue(queryNode.toString(), SqootQuery.class);//FIXME:automatic mapper.
            } catch (Exception e) {
                LOG.error(e.toString());
            }
//            SqootQuery sqootQuery = new SqootQuery();
//            sqootQuery.setPage(node.get("page"));
            LOG.info("sqootQuery:" + sqootQuery.toString());
            sqootDealsObject.setQuery(sqootQuery);
            //
            JSONObject jsonObject = new JSONObject(respString);
            LOG.info("jsonObject:" + jsonObject.toString());
            JSONArray deals = jsonObject.getJSONArray("deals");
            LOG.info("deals:" + deals.toString() + ",len:" + deals.length());
            for (int i = 0; i < deals.length(); i++) {
                JSONObject deal = deals.getJSONObject(i).getJSONObject("deal");
                //
                SqootDeal sqootDeal = objectMapper.readValue(deal.toString(), SqootDeal.class);
                LOG.info("sqootDeal:" + sqootDeal.toString());
                //
                SqootDealObject sqootDealObject = new SqootDealObject();
                sqootDealObject.setDeal(sqootDeal);
                //
                sqootDealsObject.getDeals().add(sqootDealObject);
//                LOG.info("sqootDealsObject.getDeals():" + sqootDealsObject.getDeals());
            }
//            LOG.info("objectMapper.readTree:deals:" + node.get("deals").toString());
//            sqootDealsObject = objectMapper.readValue(node.get("deals").toString(), SqootDealsObject.class);
            LOG.info("sqootDealsObject:" + sqootDealsObject.toString());
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return sqootDealsObject;
    }

    //
    @Override
    public List<SqootCategoryObject> getCategories() {
        String url = "http://api.sqoot.com/v2/categories?api_key=" + sqootApiKey;
        LOG.info("sqootCategories url:" + url);
        SqootCategories sqootCategories = (SqootCategories) getRestTemplate().getForObject(url, SqootCategories.class);
//        Collection<SqootCategory> readValues = new ObjectMapper().readValue(jsonAsString, new TypeReference<Collection<SqootCategory>>() { });
//        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);
//        LOG.info("sqootCategories responseEntity:" + responseEntity);
//        SqootCategory sqootCategories = (SqootCategory) responseEntity.getBody();
//        List<SqootCategory> sqootCategories = restTemplate.getForObject("http://api.sqoot.com/v2/categories?api_key={api_key}", ArrayList.class, SocialApiConfig.getSqootApiKey());
        LOG.info("sqootCategories:" + sqootCategories);
        return sqootCategories.getCategories();
    }

    protected RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //set interceptors/requestFactory
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new LoggingRequestInterceptor();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<ClientHttpRequestInterceptor>();
        clientHttpRequestInterceptors.add(clientHttpRequestInterceptor);
        restTemplate.setInterceptors(clientHttpRequestInterceptors);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        return restTemplate;
    }
}
