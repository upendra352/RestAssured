package com.restAssured.RestAssuredProject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RateLimiting {
	
//	8️⃣ Rate Limiting Testing (Advanced)
//	Simulate repeated API calls and detect HTTP 429:
//	for (int i = 0; i < 100; i++) {
//	    Response res = given().get("/rate-limited");
//	    if (res.getStatusCode() == 429) {
//	        break;
//	    }
//	}
	
	
	/* ✅ Step 3: RestAssured Test Code
	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;

	import io.restassured.response.Response;
	import org.testng.annotations.Test;

	public class RateLimitTest {

	    @Test
	    public void testRateLimitingSimulation() {
	        baseURI = "http://localhost:8080";

	        for (int i = 1; i <= 5; i++) {
	            Response response = given()
	                .when().get("/limited");

	            int code = response.getStatusCode();
	            System.out.println("Request #" + i + " - Status: " + code);

	            if (code == 429) {
	                String retry = response.getHeader("Retry-After");
	                System.out.println("⛔ Rate limited. Retry after: " + retry + " seconds");
	                break;
	            }
	        }
	    }
	}
	✅ Output:
	bash
	Copy
	Edit
	Request #1 - Status: 200
	Request #2 - Status: 200
	Request #3 - Status: 200
	Request #4 - Status: 429
	⛔ Rate limited. Retry after: 10 seconds */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		given()
	    .baseUri("https://api.example.com")
	.when()
	    .get("/resource")
	.then()
	    .statusCode(anyOf(equalTo(200), equalTo(429))); // handle both success and rate limit

	}

}
