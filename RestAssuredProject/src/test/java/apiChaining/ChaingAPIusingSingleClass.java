package apiChaining;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class ChaingAPIusingSingleClass {
	
	static final String BASE_URI="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN="124740c0ab3f70e3ca6ba67aa58556a2cf6e02335ce7b6101437f4af688aa1dd";
	int userId;
	
	Faker faker=new Faker();
	
	@Test
	void createUser() {
		
		JSONObject requestData=new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "inactive");
		
		System.out.println(requestData);
		
	userId=given()
	   .header("Authorization","Bearer "+BEARER_TOKEN)
	   .contentType("application/json")
	   .body(requestData.toString())
	.when()
	    .post(BASE_URI)
	.then().statusCode(201).log().body().extract().response().jsonPath().getInt("id");
	    
	}
	
	@Test(dependsOnMethods= {"createUser"})
	void getUser() {
		
		given().header("Authorization","Bearer "+BEARER_TOKEN).pathParam("id", userId)
//		.when().get(BASE_URI+"/{userId}")
		.when().get(BASE_URI+"/{id}")
		.then().statusCode(200).log().body();
	}
	
	@Test(dependsOnMethods= {"getUser"})
	void updateUser() {
		
		JSONObject requestData=new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "Male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "active");
		
	userId=given()
	   .header("Authorization","Bearer "+BEARER_TOKEN)
	   .contentType("application/json")
	   .body(requestData.toString()).pathParam("id", userId)
	.when()
	    .put(BASE_URI+"/{id}")
	.then().statusCode(200).log().body().extract().response().jsonPath().getInt("id");
	    
	}
	
	@Test(dependsOnMethods= {"updateUser"})
	void deleteUser() {
		given().header("Authorization","Bearer "+BEARER_TOKEN).pathParam("id", userId)
		.when().delete(BASE_URI+"/{id}")
		.then().statusCode(204);
	}

}
