package Weather;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class OpenWeatherMap_API_PositiveTest extends OpenWeatherMap_Base
{

	private static final String API_Key="da9b4326dfc4b6a06767b8c0005e154d";
	
	@Test
	public void testWeatherByCity()
	{
		ExtentTest extentTest = extent.createTest("HappyPath1Test1");
        test.set(extentTest);
        
		Response response=given()
		.queryParam("q","London")
		.queryParam("appid",API_Key)
	.when()
		.get("/weather");
	response.then()
		.statusCode(200)
		.body("name", equalTo("London"))
		.body("cod", equalTo(200));
		//Assert.assertTrue(true);
	 test.get().pass("Successfully retrieved weather for London");
        
	}
	
	@Test
	public void testWeatherByCoordinate()
	{
		ExtentTest extentTest = extent.createTest("HappyPath1Test2");
        test.set(extentTest);
Response response=given()
			.queryParam("lat",44.34)
			.queryParam("lon",10.99)
			.queryParam("appid",API_Key)
		.when()
			.get("/weather");
		response.then()
			.statusCode(200)
			.body("coord.lat", equalTo(44.34F))
			.body("coord.lon", equalTo(10.99F));
		//Assert.assertTrue(true);
		 test.get().pass("Successfully retrieved weather for coordinates 35, 139");

		
	}
}
