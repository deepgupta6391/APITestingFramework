package com.w2a.APITestingFramework.testcases;

import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.DeleteCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setUp.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;
import com.w2a.APITestingFramework.utilities.TestUtil;

import io.restassured.response.Response;

public class DeleteCustomerTest extends BaseTest{

	/**
	 * @param data
	 */
	@Test(dataProviderClass = DataUtil.class,dataProvider = "data")
	public void deleteCustomer(Hashtable<String, String> data) {

		Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);
		response.prettyPrint();
		ExtentListeners.testReport.get().info(data.toString());
		
		System.out.println("Status code is :"+response.getStatusCode());		
		
		/*
		 * String actual_id=response.jsonPath().get("id").toString();
		 * System.out.println("Actual ID is :"+actual_id);
		 * Assert.assertEquals(actual_id, data.get("id"),"IS doesn't match");
		 */
		 
		/*
		 * JSONObject jsonObject=new JSONObject(response.asString());
		 * System.out.println(jsonObject.has("id"));
		 * Assert.assertTrue(jsonObject.has("id")
		 * ,"ID key is not present in the json respponse");
		 */
		
		System.out.println("Presence check for Object Key : "+TestUtil.jsonHasKey(response.asString(), "object"));
		System.out.println("Presence check for Deleted Key : "+TestUtil.jsonHasKey(response.asString(), "deleted"));
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"ID key is not present in the JSON response");
		
		String actual_id=TestUtil.getJsonKeyValue(response.asString(), "id");
		Assert.assertEquals(actual_id,data.get("id"));
		System.out.println("Object key value is : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Deleted key value is : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
		
	}	
}
