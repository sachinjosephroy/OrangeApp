package com.orangeapp.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangeapp.qa.base.Testbase;

public class HomePageTests extends Testbase {
	
	public HomePageTests() {
		super();
	}
	
	@Test(priority=1, description="DESCRIPTION: Verify logo text of the Home page")
	public void testLogoText() {
		String actual = home.getLogoText();
		String expected = "#1 Free CRM software in the cloud for sales and service";
		verifyResultString(actual, expected);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(priority=2, description="DESCRIPTION: Verify Setup link text of the Home page")
	public void testSetupLinkText() {
		String actual = home.getSetupLinkText();
		String expected = "#1 Free CRM software in the cloud for sales and servicee";
		verifyResultString(actual, expected);
		Assert.assertEquals(actual, expected);
	}

}
