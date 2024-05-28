package Weather;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;


public class OpenWeatherMap_API_NegativeTest extends OpenWeatherMap_Base
{

	private static final String API_Key="da9b4326dfc4b6a06767b8c0005e154d";
	
	@Test
	public void testCityNotFound()
	{
		ExtentTest extentTest = extent.createTest("HappyPath2Test1");
        test.set(extentTest);

	        Response response =given()
		.queryParam("q","L")
		.queryParam("appid",API_Key)
	.when()
		.get("/weather");
	        
	response.then()
		.statusCode(404)
		.body("message", equalTo("city not found"));
	//Assert.assertTrue(true);
	 test.get().pass("Received 404 status code for city not found");
	}
	
	@Test
	public void testInvalidKey()
	{
		ExtentTest extentTest = extent.createTest("HappyPath2Test2");
        test.set(extentTest);
Response response=given()
			.queryParam("lat",44.34)
			.queryParam("lon",10.99)
			.queryParam("appid","Invalid")
		.when()
			.get("/weather");
		response.then()
			.statusCode(401)
			.body("message", equalTo("Invalid API key. Please see https://openweathermap.org/faq#error401 for more info."));
		//Assert.assertTrue(true); 
		test.get().pass("Received 401 status code with invalid API key");

	}
}
