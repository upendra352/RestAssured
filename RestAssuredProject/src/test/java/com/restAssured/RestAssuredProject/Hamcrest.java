package com.restAssured.RestAssuredProject;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class Hamcrest {

	public static void main(String[] args) {
		
RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		
	/*	Response response=given().contentType("application/json")
				.when().get("/posts/1/comments")
				.then().log().all().statusCode(200).extract().response(); */
		
		
//		response.body("id", instanceOf(Integer.class));
//		But this won't work directly with the Response object. Here's why and how to fix it:
//		
//		❌ Problem:
//			response.body(...) is not a valid method on Response for doing Hamcrest assertions.
//			You need to do such validations during .then(), using RestAssured's fluent API and Hamcrest matchers.
		
        given()
            .contentType("application/json")
        .when()
            .get("/posts/1/comments")
        .then()
            .statusCode(200)
            .body("email", hasItem("Eliseo@gardner.biz"))
            .body("[0].email",containsString("@gardner"))
            .body("id", hasItems(1,2,3,4,5))
            .body("[0].id", equalTo(1))
            .body("[0].id", instanceOf(Integer.class)); // ✅ check type of first `id`
        System.out.println("Type of id fiele is matched");
        
//        ✅ 5. Loop + Custom Validation Using JsonPath (Optional)
//        If you need logic like checking that all IDs are integers:

       
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
            .when()
                .get("/posts/1/comments")
            .then()
                .statusCode(200)
                .extract().response();

        List<Object> ids = response.jsonPath().getList("id");

        for (Object id : ids) {
            if (!(id instanceof Integer)) {
                throw new AssertionError("ID is not an Integer: " + id);
            }
        }
        System.out.println("✅ All IDs are Integers.");
        
        List<Object> names=response.jsonPath().getList("name");
        for(Object name:names) {
        	if(!(name instanceof String)) {
        		throw new AssertionError("Name is not an Integer: "+name);
        		
        	}
        }
        System.out.println("✅ All Names are Strings.");

	}

}
