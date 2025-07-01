package com.restAssured.RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

public class Cookies {

	public static void main(String[] args) {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		
		Response response=given().contentType("application/json")
					.when().get("/posts/1/comments")
					.then().log().all().statusCode(200).extract().response(); 
		
		Map<String, String> sessionId=response.getCookies();
		String serverHeader = response.getHeader("Server");
		System.out.println("Cookies:"+serverHeader);

	}

}
