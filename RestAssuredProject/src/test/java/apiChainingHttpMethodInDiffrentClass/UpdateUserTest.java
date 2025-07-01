package apiChainingHttpMethodInDiffrentClass;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUserTest {
	
	static final String BASE_URI="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN="124740c0ab3f70e3ca6ba67aa58556a2cf6e02335ce7b6101437f4af688aa1dd";
	
	Faker faker=new Faker();
	
	
	@Test(dependsOnMethods= {"apiChainingHttpMethodInDiffrentClass.GetUserTest.getUser"})
	void updateUser(ITestContext context) {
		
		JSONObject requestData=new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "Male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "active");
		
	given()
	   .header("Authorization","Bearer "+BEARER_TOKEN)
	   .contentType("application/json")
	   .body(requestData.toString()).pathParam("id", (Integer)context.getAttribute("userId"))
	.when()
	    .put(BASE_URI+"/{id}")
	.then().statusCode(200).log().body().extract().response().jsonPath().getInt("id");
	    
	}

}
