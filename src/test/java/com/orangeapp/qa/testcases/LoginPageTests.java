package com.orangeapp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangeapp.qa.base.Testbase;

public class LoginPageTests extends Testbase {
	
	public LoginPageTests() {
		super();
	}
	
	@Test(priority=1, description="DESCRIPTION: Verify title of the Login page")
	public void testTitle() {
		String actual = login.getTitle();
		String expected = "#1 Free CRM software in the cloud for sales and service";
		//verifyResultString();
		Assert.assertEquals(actual, expected);
	}

}
