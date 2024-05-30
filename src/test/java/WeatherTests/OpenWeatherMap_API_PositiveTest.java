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

public class OpenWeatherMap_API_PositiveTest extends OpenWeatherMap_Base {

    @Test
    public void testGetWeatherByCityName() {
    	ExtentTest extentTest = extent.createTest("HappyPath2Test1");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", "London");
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"));
        test.get().pass("Received 404 status code for city not found");

    }
}
