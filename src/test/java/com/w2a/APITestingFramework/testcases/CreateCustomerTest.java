package com.w2a.APITestingFramework.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setUp.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;

import io.restassured.response.Response;

public class CreateCustomerTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class,dataProvider = "data")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {

		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
		response.prettyPrint();
		ExtentListeners.testReport.get().info(data.toString());
		
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	@Test(dataProviderClass = DataUtil.class,dataProvider = "data")
	public void validateCreateCustomerAPIWithInvalidSecretKey(Hashtable<String, String> data) {

		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
		response.prettyPrint();
		ExtentListeners.testReport.get().info(data.toString());
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	
}
