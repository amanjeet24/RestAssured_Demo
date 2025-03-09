package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadDownload {
	
	//@Test
	void singleFileUpload()
	
	{
		File myfile=new File("D:\\API Postman Workspace\\json files\\test1.txt");
		
		given()
			.multiPart("file", myfile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadFile")
			
		.then()
			.statusCode(200)
			.body("fileName", equalTo("test1.txt"))
			.log().all();
		
	}
	//@Test
	void MultipleFilesUpload()
	
	{
		File myfile1=new File("D:\\API Postman Workspace\\json files\\test1.txt");
		File myfile2=new File("D:\\API Postman Workspace\\json files\\test2.txt");
		
		given()
			.multiPart("files", myfile1)
			.multiPart("files", myfile2)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
			
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("test1.txt"))
			.body("[1].fileName", equalTo("test2.txt"))
			.log().all();
		
	}
	
	@Test
	void fileDownload()
	{
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/test1.txt")
			
		.then()
			.statusCode(200)
			.log().body();
	}
}
