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
    	ExtentTest extentTest = extent.createTest("HappyPath2Test1");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", "InvalidCityName");
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "city not found");
   	 test.get().pass("Received 404 status code for city not found");

    }
/*
    @Test
    public void testMissingApiKey() {
        Map<String, String> params = new HashMap<>();
        params.put("q", "London");

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertEquals(response.jsonPath().getString("message"), "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.");
    }
    */
}
