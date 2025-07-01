package apiChainingHttpMethodInDiffrentClass;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUserTest {
	
	static final String BASE_URI="https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN="124740c0ab3f70e3ca6ba67aa58556a2cf6e02335ce7b6101437f4af688aa1dd";
	
	@Test(dependsOnMethods= {"apiChainingHttpMethodInDiffrentClass.UpdateUserTest.updateUser"})
	void deleteUser(ITestContext context) {
		given().header("Authorization","Bearer "+BEARER_TOKEN).pathParam("id", (Integer)context.getAttribute("userId"))
		.when().delete(BASE_URI+"/{id}")
		.then().statusCode(204);
	}

}
