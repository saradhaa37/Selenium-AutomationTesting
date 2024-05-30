package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.Map;

public class ApiUtils {
    public static Response getRequest(String endpoint, Map<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}
