package Weather;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class TestWeather {
	
	@Test
	public void test()
	{
		
		baseURI="https://api.openweathermap.org/data/2.5/weather";
		/*
		Response response=get("?lat=44.34&lon=10.99&appid=da9b4326dfc4b6a06767b8c0005e154d");
		System.out.print(response.getStatusCode());
		System.out.println(response.getBody().asString());
		*/
		Response response1=get("?lat=44.34&lon=10.99&appid=xx");
		System.out.print(response1.getStatusCode());
		System.out.println(response1.getBody().asString());
		
		Response response2=get("?q=L&appid=da9b4326dfc4b6a06767b8c0005e154d");
		System.out.print(response2.getStatusCode());
		System.out.println(response2.getBody().asString());
	}
	}


