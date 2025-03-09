package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class ParsingJSONResponseData {

	
	//@Test
	void testJsonResponse()
	{
		//Approach 1
		
//		given()
//			.contentType("ContentType.JSON")
//		
//		.when()
//			.get("http://localhost:3000/store")
//		.then()
//			.statusCode(200)
//			.header("Content-Type", "application/json")
//			.body("book[3].title", equalTo("The Lord of the Rings"));
		
		
		
		//Approach2
		
		Response res=given()
			.contentType("ContentType.JSON")
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/json");
		
		String bookname= res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");
	}
	
	
	
	@Test
	void testJsonResponseBodyData()
	{
		
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		//JSONObject class
		
		JSONObject jo=new JSONObject(res.asString());
		
//		//print all the books title
//		for(int i=0; i<jo.getJSONArray("book").length(); i++)
//		{
//			String bookTitle= jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//			System.out.println(bookTitle);
//		}
		
		//to check particular title present or not
		
		boolean status=false;
		
				for(int i=0; i<jo.getJSONArray("book").length(); i++)
				{
					String bookTitle= jo.getJSONArray("book").getJSONObject(i).get("title").toString();

					if(bookTitle.equals("The Lord of the Rings"))
					{
						status=true;
						break;
					}
				}
				Assert.assertEquals(status, true);
				
				
				//validate total price of books
				
				double totalprice=0;
				
				for(int i=0; i<jo.getJSONArray("book").length(); i++)
				{
					String price= jo.getJSONArray("book").getJSONObject(i).get("price").toString();
					
					totalprice=totalprice+Double.parseDouble(price);
				}
				System.out.println("Total price of books...." + totalprice);
				Assert.assertEquals(totalprice, 526.0);
		}
}
