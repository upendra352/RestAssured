package requestResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;

import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ListJsonPath {

	public static void main(String[] args) {
	
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/")
				.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		Response response=given().spec(req).when().get("/posts/1/comments").then().log().all().spec(resSpec)
				.extract().response();
		
		List<Object>ids=response.jsonPath().getList("id");
		System.out.println(ids.get(2));

	        for (Object id : ids) {
	            if (!(id instanceof Integer)) {
	                throw new AssertionError("ID is not an Integer: " + id);
	            }
	        }
	        System.out.println("✅ All IDs are Integers.");
	    
	        
	        List<String>emails=response.jsonPath().getList("email");
	        for(String email:emails) {
	        	if(!(email.contains("@"))) {
	        		throw new AssertionError("Email does not contain @: " + email);
	        	}
	        }
	        System.out.println(" All Emails contains @.");

	}


}
