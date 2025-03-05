package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.Set;

public class CookiesDemo {
	
	//@Test
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com")
			
		.then()
			.cookie("AEC", "AVcja2e18nsoY4KJ8-N2lJQBXPrk9_2_KwJJENuU0sxiF8hCCfFw5MVfAPg")  //every time cookie value is keep changing, so our test will fail. but it is passed logically
			.log().all();
		
	}
	
	@Test
	void getCookiesInfo()
	{
		Response res=given()
		
				.when()
					.get("https://www.google.com");
		
		//get single cookie info
//		String cookie_value=res.getCookie("AEC");
//		System.out.println("Value of cookie is...."+cookie_value);
		
		//get all cookies info
		Map<String,String> cookies_values=res.getCookies();
		
		//System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(k);
			System.out.println(k+"      "+cookie_value);
		
		}
			
		
	}

}
