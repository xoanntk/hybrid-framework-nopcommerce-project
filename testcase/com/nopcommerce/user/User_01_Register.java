package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGenerator;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserRegisterPageObject;

public class User_01_Register extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName, emailAddress, password, invalidEmail, incorrectPassword;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeClass()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "abc" + getRandomNumber() + "@gmail.com";
		invalidEmail = "abc@abc@.com.vn";
		incorrectPassword = "1234";
	}

	@Test
	public void Register_01_Empty_Data() {
		log.info("Register_01 - Step 01: click to Register link");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Register_01 - Step 02: click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_01 - Step 03: Verify error message displayed");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "FirstName-error"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "LastName-error"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "Email-error"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "Password-error"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "ConfirmPassword-error"), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		log.info("Register_02 - Step 01: click to Register link");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Register_02 - Step 02: Input to required fields");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		registerPage.inputToTextboxByID(driver, "Email", invalidEmail);
		registerPage.inputToTextboxByID(driver, "Password", password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register_02 - Step 03: click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_02 - Step 04: Verify error message displayed");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "Email-error"), "Wrong email");

	}

	@Test
	public void Register_03_Success() {
		log.info("Register_03 - Step 01: click to Register link");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Register_03 - Step 02: Input to required fields");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		registerPage.inputToTextboxByID(driver, "Password", password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register_03 - Step 03: click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_03 - Step 04: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register_03 - Step 05: click to Logout link");
		registerPage.openPageAtHeader(driver, "ico-logout");
		homePage = PageGenerator.getUserHomePage(driver);
	}

	@Test
	public void Register_04_Existing_Email() {
		log.info("Register_04 - Step 01: click to Register link");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Register_04 - Step 02: Input to required fields");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		registerPage.inputToTextboxByID(driver, "Password", password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register_04 - Step 03: click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_04 - Step 04: Verify error existing email message displayed");
		verifyEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		log.info("Register_05 - Step 01: click to Register link");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Register_05 - Step 02: Input to required fields");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		registerPage.inputToTextboxByID(driver, "Password", incorrectPassword);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", incorrectPassword);

		log.info("Register_05 - Step 03: click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_05 - Step 04: Verify error message displayed");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "Password-error"), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		log.info("Register_06 - Step 01: click to Register link");
		homePage.openPageAtHeader(driver, "ico-register");
		registerPage = PageGenerator.getUserRegisterPage(driver);

		log.info("Register_06 - Step 02: Input to required fields");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		registerPage.inputToTextboxByID(driver, "Password", password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", "1234567");

		log.info("Register_06 - Step 03: click to Register button");
		registerPage.clickToRegisterButton();

		log.info("Register_06 - Step 04: Verify error message displayed");
		verifyEquals(registerPage.getErrorMessageTextboxByID(driver, "ConfirmPassword-error"), "The password and confirmation password do not match.");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
