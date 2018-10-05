package com.orangeapp.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangeapp.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testbase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static LoginPage login;
	
	String configFilePath = "C:\\Users\\simir\\eclipse-workspace\\OrangeApp\\src\\main\\java\\com\\orangeapp\\qa\\configuration\\config.properties";
	String extentReportPath = "C:\\Users\\simir\\eclipse-workspace\\OrangeApp\\ExtentReports\\OrangeAppReport.html";
	String extentConfigPath = "C:\\Users\\simir\\eclipse-workspace\\OrangeApp\\ExtentReports\\extent-config.xml";
	
	public Testbase() {
		try {
			FileInputStream fis = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void initializeBrowser() {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("driverpath"));
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("driverpath"));
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	public void initializePages() {
		login = new LoginPage(driver);
	}
	
	@BeforeSuite
	public void setUpSuite() {
		extent = new ExtentReports(extentReportPath);
		extent.loadConfig(new File(extentConfigPath));
	}
	
	@BeforeMethod
	public void setUpMethod(Method method) {
		test = extent.startTest(this.getClass().getSimpleName() + " :: " + method.getName(), method.getName());
		test.assignAuthor("Sachin Roy");
		test.assignCategory("Functional Tests");
		
		initializeBrowser();
		initializePages();
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Testing failed");
			extent.endTest(test);
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Testing skipped");
			extent.endTest(test);
		}
		else {
			test.log(LogStatus.PASS, "Testing passed");
			extent.endTest(test);
		}
		driver.quit();
	}
	
	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
		extent.close();
	}
	
	public void verifyResultString(String actual, String expected) {
		if(!actual.equals(expected)) {
			test.log(LogStatus.FAIL, "EXP RESULT: " + expected + "<br/>" + "ACT RESULT: " + actual);
		}
	}

}
