package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	@Test
	void testLogs()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			//.log().all();
			//.log().body();
			//.log().cookies();
			.log().headers();
	}

}
