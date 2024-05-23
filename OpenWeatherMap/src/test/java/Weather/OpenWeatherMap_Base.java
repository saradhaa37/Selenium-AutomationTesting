package Weather;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;

public class OpenWeatherMap_Base 
{
	@BeforeClass
	public static void setup() 
	{
		RestAssured.baseURI="https://api.openweathermap.org/data/2.5";
	}
	
	@AfterClass
	public static void teardown()
	{
	

	}
}
