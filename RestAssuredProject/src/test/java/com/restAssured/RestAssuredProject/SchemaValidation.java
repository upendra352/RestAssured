package com.restAssured.RestAssuredProject;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.File;

public class SchemaValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		try {
		given().contentType("application/json")
				.when().get("/posts/1/comments")
//				.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
				.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(
						new File(System.getProperty("user.dir")+"\\src\\test\\schema.json")));
		System.out.println("✅ Schema validation passed.");
		}
		catch(Exception e) {
			System.out.println("❌ Schema validation failed: " + e.getMessage());
			
		}

	}

}
