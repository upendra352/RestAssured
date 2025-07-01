package com.restAssured.RestAssuredProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetMethod {

	public static void main(String[] args) {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		
		Response response=given().contentType("application/json")
				.when().get("/posts/1/comments")
				.then().log().all().statusCode(200).extract().response();
		
//		JsonPath js=new JsonPath("response");//wrongstatement
		JsonPath js=new JsonPath(response.asString());
		
		// Convert response to JsonPath
//        JsonPath js = response.jsonPath();
		
		int count =js.getInt("size()");
		System.out.println(count);
		for(int i=0;i<count;i++) {
			int idValue=js.getInt("[" +i+ "].id");
			System.out.print(idValue+" : ");
			String email=js.getString("["+i+"].email");
			System.out.print(email+" , ");
		}
		int idVal=js.getInt("[0].id");
		System.out.println(idVal);

	}

}
