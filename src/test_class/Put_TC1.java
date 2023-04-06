package test_class;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_methods.Put_common_method;
import common_methods.common_method_utilities;
import io.restassured.path.json.JsonPath;
import request_repository.Put_request_repository;

public class Put_TC1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String responseBody = "" ;
		int responseStatuscode = 0;
		String baseUri = Put_request_repository.baseuri();
		String resource = Put_request_repository.resource();
		String requestBody = Put_request_repository.Put_request_tc1();
		for(int i=0 ; i<5 ; i++) 
        {
		 responseStatuscode = Put_common_method.responeStatuscode_extractor(baseUri, resource, requestBody);	
          if (responseStatuscode == 200)
		  {
			responseBody = Put_common_method.responseBody_extractor(baseUri, resource, requestBody);
			responseBodyValidator(responseBody);
			
			break;
	      }
          else
          {
        	  System.out.println("correct status code is not found in the iteration " +i);
          }
        } 
		common_method_utilities.evidenceFileCreator("Put_tc1",requestBody,responseBody);
		AssertJUnit.assertEquals(responseStatuscode, 200);
	}
	
	public static void responseBodyValidator(String responseBody)
	{
		// create jsonPath object to extract responsebody parameters
			JsonPath jsp = new JsonPath(responseBody);
			
			// extract response body parameter
			String res_name =jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_updatedAt =jsp.getString("updatedAt");
			
			System.out.println("name : " + res_name + "\njob : " + res_job + "\nupdatedAt : " + res_updatedAt);
			
			//validate response body parameter
			AssertJUnit.assertEquals(res_name, "morpheus");
			AssertJUnit.assertEquals(res_job, "zion resident");
			
			//extract date from updated parameter
			String actual_date =res_updatedAt.substring(0,10);
			System.out.println(actual_date);
			String current_date = LocalDate.now().toString();
			AssertJUnit.assertEquals(actual_date, current_date);
	}

}
