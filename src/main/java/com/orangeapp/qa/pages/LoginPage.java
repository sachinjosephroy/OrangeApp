package com.orangeapp.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeapp.qa.base.Testbase;

public class LoginPage extends Testbase {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "")
	WebElement tbUsername;
	
	@FindBy(xpath = "")
	WebElement tbPassword;
	
	@FindBy(xpath = "")
	WebElement btnLogin;
	
	public void logMeIn() {
		tbUsername.sendKeys(prop.getProperty("username"));
		tbPassword.sendKeys(prop.getProperty("password"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments(0).click()", btnLogin);
	}

	public String getTitle() {
		return driver.getTitle();
	}

}
