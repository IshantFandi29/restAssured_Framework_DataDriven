package common_methods;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Put_common_method {
	
	public static int responeStatuscode_extractor(String baseuri, String resource, String requestBody)
	{
		RestAssured.baseURI= baseuri;
		int responseStatuscode= given().header("Content-Type", "application/json").body(requestBody)
				.when().put(resource).then().extract().statusCode();
		return responseStatuscode;
	}
	
	public static String responseBody_extractor(String baseuri, String resource, String requestBody)
	{
		RestAssured.baseURI= baseuri;
		String response_body= given().header("Content-Type", "application/json").body(requestBody)
				.when().put(resource).then().extract().response().asString();
		return response_body;
	}

}
