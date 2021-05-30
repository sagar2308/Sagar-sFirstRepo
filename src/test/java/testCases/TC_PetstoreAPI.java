package testCases;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;

public class TC_PetstoreAPI {
	
	@Test(priority=1)
	public void test_createUser() {
		
		HashMap data=new HashMap();
		
		data.put("id", 0);
		data.put("username", "PTUser");
		data.put("fistName","PT");
		data.put("lastName", "LN");
		data.put("email", "ptuser@mailinator.com");
		data.put("password", "Test@123");
		data.put("phone", "0123456789");
		data.put("userStatus", 1);
		
		Response res=
	
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://petstore.swagger.io/v2/user")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("unknown"),true);
		
	}
	@Test(priority=2)
	public void test_getUser() {
		
		
		
		 given()
			.pathParam("username", "PTUser")
		.when()
			.get("https://petstore.swagger.io/v2/user/{username}")
		.then()
			.statusCode(200)
			.log().body()
			.assertThat()
			.body("email",is("ptuser@mailinator.com"));
		 	
		 expect().body(containsString("Test@123"));

	}
	
}
