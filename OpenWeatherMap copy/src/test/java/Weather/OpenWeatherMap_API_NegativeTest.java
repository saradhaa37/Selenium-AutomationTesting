package Weather;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenWeatherMap_API_NegativeTest extends OpenWeatherMap_Base
{

	private static final String API_Key="da9b4326dfc4b6a06767b8c0005e154d";
	
	@Test
	public void testCityNotFound()
	{
        test.set(extent.createTest("testCityNotFound", "Test with invalid city name"));

		given()
		.queryParam("q","L")
		.queryParam("appid",API_Key)
	.when()
		.get("/weather")
	.then()
		.statusCode(404)
		.body("message", equalTo("city not found"));
        test.get().pass("Received 404 status code for city not found");

	}
	
	@Test
	public void testInvalidKey()
	{
        test.set(extent.createTest("testCityNotFound", "Test with invalid city name"));

		given()
			.queryParam("lat",44.34)
			.queryParam("lon",10.99)
			.queryParam("appid","Invalid")
		.when()
			.get("/weather")
		.then()
			.statusCode(401)
			.body("message", equalTo("Invalid API key. Please see https://openweathermap.org/faq#error401 for more info."));
        test.get().pass("Received 401 status code with invalid API key");

	}
}
