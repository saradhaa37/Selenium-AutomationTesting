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
    	ExtentTest extentTest = extent.createTest("TC01_ValidCityWeatherRetrieval");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("validCity"));
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"));
        test.get().pass("Received 200 status for city found");

    }
    
    @Test
    public void testValidCityWeatherRetrievalInDifferentFormat() {
        ExtentTest extentTest = extent.createTest("TC08_ValidCityWeatherRetrievalInDifferentFormat");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("validCity"));
        params.put("appid", ConfigManager.getProperty("api.key"));
        params.put("mode", "xml");

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().contains("<current>"));
        test.get().pass("Received 200 status code for valid city in XML format");

        params.put("mode", "json");
        response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"));
        test.get().pass("Received 200 status code for valid city in JSON format");
    }

    @Test
    public void testValidCityWeatherRetrievalByCoordinates() {
        ExtentTest extentTest = extent.createTest("TC10_ValidCityWeatherRetrievalByCoordinates");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("lat", testData.get("validLat"));
        params.put("lon", testData.get("validLon"));
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"));
        test.get().pass("Received 200 status code for valid city by coordinates");
    }

    @Test
    public void testValidCityWeatherRetrievalByCityID() {
        ExtentTest extentTest = extent.createTest("TC11_ValidCityWeatherRetrievalByCityID");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("id", testData.get("validCityId"));
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"));
        test.get().pass("Received 200 status code for valid city by ID");
    }

    @Test
    public void testValidCityWeatherRetrievalWithUnits() {
        ExtentTest extentTest = extent.createTest("TC12_ValidCityWeatherRetrievalWithUnits");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("validCity"));
        params.put("units", "metric");
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("main.temp"));
        test.get().pass("Received 200 status code for valid city with units");
    }

    @Test
    public void testValidCityWeatherRetrievalWithLanguage() {
        ExtentTest extentTest = extent.createTest("TC13_ValidCityWeatherRetrievalWithLanguage");
        test.set(extentTest);
        Map<String, String> params = new HashMap<>();
        params.put("q", testData.get("validCity"));
        params.put("lang", "es");
        params.put("appid", ConfigManager.getProperty("api.key"));

        Response response = ApiUtils.getRequest("/weather", params);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"));
        test.get().pass("Received 200 status code for valid city with language");
    }
}
