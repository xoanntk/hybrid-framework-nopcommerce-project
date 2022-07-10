package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGenerator;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserRegisterPageObject;

public class Common_01_Register extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	public static String email, password, firstName, lastName, fullName;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeTest()
	public void Register(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FA";
		fullName = firstName + " " + lastName;

		password = "12345678";
		email = "afc" + getRandomNumber() + "@gmail.com";
		log.info("Pre-condition - Step 01: Navigate to 'Register' page");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Pre-condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Pre-condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);

		log.info("Pre-condition - Step 04: Enter to Email textbox with value is '" + email + "'");
		registerPage.inputToTextboxByID(driver, "Email", email);

		log.info("Pre-condition - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Pre-condition - Step 07: Click to 'Register' button");
		registerPage.clickToButtonByID(driver, "register-button");

		log.info("Pre-condition - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Pre-condition - Step 09: Click to Logout link");
		registerPage.openPageAtHeader(driver, "ico-logout");
		homePage = PageGenerator.getUserHomePage(driver);

		driver.quit();
	}
}
