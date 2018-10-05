package com.orangeapp.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeapp.qa.base.Testbase;

public class HomePage extends Testbase {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[@class='logo_text']")
	WebElement txtLogo;
	
	@FindBy(xpath = "//i[@class='fa fa-gears icon-2x']")
	WebElement lnkSetup;
	
	
	public void switchFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public void login() {
		login.logMeIn();
	}
	
	public String getLogoText() {
		login();
		switchFrame();
		return txtLogo.getText();
	}
	
	public String getSetupLinkText() {
		login();
		switchFrame();
		return lnkSetup.getText();
	}

}
