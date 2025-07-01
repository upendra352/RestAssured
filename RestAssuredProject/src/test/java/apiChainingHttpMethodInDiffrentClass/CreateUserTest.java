package apiChainingHttpMethodInDiffrentClass;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUserTest {
	
	static final String BASE_URI="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN="124740c0ab3f70e3ca6ba67aa58556a2cf6e02335ce7b6101437f4af688aa1dd";
	int userId;
	
	Faker faker=new Faker();
	
	@Test
	void createUser(ITestContext context) {
		
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
	
	context.setAttribute("userId", userId);
	    
	}

}
