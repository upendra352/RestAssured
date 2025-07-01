package requestResponseSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RequestSpecExample {

	public static void main(String[] args) {
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/")
				.setContentType(ContentType.JSON).build();
		
//		Response response=given().log().all().spec(req).when().get("/posts/1/comments");
//				.then().log().all().assertThat().statusCode(200).extract().response();
//		System.out.println("Response:"+response.asString());
		
		ResponseSpecification resSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
//		Response res=given().spec(req).when().get("/posts/1/comments").then().log().all().spec(resSpec).extract().response()
//				.body("[0].postId",equalTo(1));
//		Explanation:
//			.body("[0].postId", equalTo(1)) should be before .extract().response(), because then() returns a ValidatableResponse, which supports assertions like .body().
//
//			After all validations (like .body(), .statusCode()), you call .extract().response() to get the Response object.
		
		Response res=given().spec(req).when().get("/posts/1/comments").then().log().all().spec(resSpec)
				.body("[0].postId",equalTo(1))
				.body("postId", hasItem(1))
				.body("[0].postId", instanceOf(Integer.class))
				.body("postId", hasSize(5))
				.body("[0].id", greaterThan(0))
				.extract().response();
		
		JsonPath js=new JsonPath(res.asString());
		
		int postId=js.getInt("[0].postId");
		System.out.println(postId);

	}

}
