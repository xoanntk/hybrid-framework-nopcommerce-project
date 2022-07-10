package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register;

import commons.BaseTest;
import commons.PageGenerator;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserLoginPageObject;

public class User_02_Login extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private String existingEmail, invalidEmail, notFoundEmail, validPassword, incorrectPassword;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeClass()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		existingEmail = Common_01_Register.email;
		validPassword = Common_01_Register.password;

		incorrectPassword = "65332";
		invalidEmail = "afc@afc@.com.vn";
		notFoundEmail = "afc" + getRandomNumber() + "@gmail.vn";

	}

	@Test
	public void Login_01_Empty_Data() {
		log.info("Login_01 - Step 01: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Login_01 - Step 02: click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_01 - Step 03: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageTextboxByID(driver, "Email-error"), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		log.info("Login_02 - Step 01: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Login_02 - Step 02: Input invalid email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", invalidEmail);

		log.info("Login_02 - Step 03: click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_02 - Step 03: click to Login button - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageTextboxByID(driver, "Email-error"), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		log.info("Login_03 - Step 01: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Login_03 - Step 02: Input email not existing into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", notFoundEmail);

		log.info("Login_03 - Step 03: click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_03 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		log.info("Login_04 - Step 01: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Login_04 - Step 02: Input existing email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", existingEmail);

		log.info("Login_04 - Step 03: Don't enter into 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", "");

		log.info("Login_04 - Step 04: click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_04 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		log.info("Login_05 - Step 01: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Login_05 - Step 02: Input existing email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", existingEmail);

		log.info("Login_05 - Step 03: Input incorrect password into 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", incorrectPassword);

		log.info("Login_05 - Step 04: click to Login button");
		loginPage.clickToLoginButton();

		log.info("Login_05 - Step 04: Verify error message displayed");
		verifyEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		log.info("Login_06 - Step 01: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Login_06 - Step 02: Input existing email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", existingEmail);

		log.info("Login_06 - Step 03: Input password into 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", validPassword);

		log.info("Login_06 - Step 04: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login_06 - Step 05: Verify 'My Account' link displayed");
		verifyTrue(homePage.isMyAccountLinkIsDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
