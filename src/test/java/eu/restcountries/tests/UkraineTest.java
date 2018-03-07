package eu.restcountries.tests;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.get;

public class UkraineTest {

    @Test
    public void latviaEstoniaBorderTest() throws JSONException {
        Response resp = get("http://restcountries.eu/rest/v1/");
        Assert.assertEquals(resp.getStatusCode(), 200);
        Assert.assertTrue(resp.contentType().contains("json"));
        JSONObject ukraine = null;
        JSONArray results = new JSONArray(resp.asString());
        for (int i=0; i<results.length(); i++) {
            if (results.getJSONObject(i).get("name").equals("Ukraine")) {
                ukraine = results.getJSONObject(i);
                break;
            }
        }
        Assert.assertTrue(ukraine.getDouble("area")>500000.0);
        System.out.println(ukraine.getString("name"));
        System.out.println(ukraine.getString("capital"));
        System.out.println(ukraine.getString("region"));
        System.out.println(ukraine.getInt("population"));
        System.out.println(ukraine.getJSONArray("borders"));
    }
}
