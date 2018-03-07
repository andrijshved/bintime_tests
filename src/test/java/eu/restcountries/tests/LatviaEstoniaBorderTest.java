package eu.restcountries.tests;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.get;

public class LatviaEstoniaBorderTest {

    @Test
    public void latviaEstoniaBorderTest() throws JSONException{
        Response resp = get("http://restcountries.eu/rest/v1/");
        Assert.assertEquals(resp.getStatusCode(), 200);
        Assert.assertTrue(resp.contentType().contains("json"));
        JSONArray latvianBorders = null;
        JSONArray results = new JSONArray(resp.asString());
        for (int i=0; i<results.length(); i++) {
            if (results.getJSONObject(i).get("name").equals("Latvia")) {
                latvianBorders = results.getJSONObject(i).getJSONArray("borders");
                break;
            }
        }
        String codeEstonia = null;
        for (int i=0; i<results.length(); i++) {
            if (results.getJSONObject(i).get("name").equals("Estonia")) {
                codeEstonia = results.getJSONObject(i).getString("alpha3Code");
                break;
            }
        }
        Assert.assertTrue(latvianBorders.toList().contains(codeEstonia));
    }
}
