package Weather;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OpenWeatherMap_API_PositiveTest extends OpenWeatherMap_Base
{

	private static final String API_Key="da9b4326dfc4b6a06767b8c0005e154d";
	
	@Test
	public void testWeatherByCity()
	{
		given()
		.queryParam("q","London")
		.queryParam("appid",API_Key)
	.when()
		.get("/weather")
	.then()
		.statusCode(200)
		.body("name", equalTo("London"))
		.body("cod", equalTo(200));
		
	}
	
	@Test
	public void testWeatherByCoordinate()
	{

		given()
			.queryParam("lat",44.34)
			.queryParam("lon",10.99)
			.queryParam("appid",API_Key)
		.when()
			.get("/weather")
		.then()
			.statusCode(200)
			.body("coord.lat", equalTo(44.34F))
			.body("coord.lon", equalTo(10.99F));
		
	}
}
