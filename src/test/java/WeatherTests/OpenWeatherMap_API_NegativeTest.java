package WeatherTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import utils.ApiUtils;
import utils.ConfigManager;
import utils.OpenWeatherMap_Base;

import java.util.HashMap;
import java.util.Map;

public class OpenWeatherMap_API_NegativeTest extends OpenWeatherMap_Base {

	
	
    @Test
    public void testInvalidCityWeather() {
    	ExtentTest extentTest = extent.createTest("TC02_InvalidCityWeatherRetrieval");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("invalidCity"));
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "city not found");
   	 test.get().pass("Received 404 status code for city not found");

    }
    @Test
    public void testMissingApiKey() {
        ExtentTest extentTest = extent.createTest("TC03_MissingApiKey");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("validCity"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.");
        test.get().pass("Received 401 status code for missing API key");
    }

    @Test
    public void testInvalidApiKey() {
        ExtentTest extentTest = extent.createTest("TC04_InvalidApiKey");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("validCity"));
        params.put("appid", testData.get("invalidApiKey"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.");
        test.get().pass("Received 401 status code for invalid API key");
    }

    @Test
    public void testEmptyCityName() {
        ExtentTest extentTest = extent.createTest("TC05_EmptyCityName");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", "");
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertTrue(response.jsonPath().getString("message").contains("Nothing to geocode"));
        test.get().pass("Received 400 status code for empty city name");
    }


    
}
