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

public class User_05_Sort_Display_Paging extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private String email, password;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeClass()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		email = Common_01_Register.email;
		password = Common_01_Register.password;

		log.info("Pre-condition - Step 02: Navigate to Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Pre-condition - Step 03: Input email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", email);

		log.info("Pre-condition - Step 04: Input password into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 05: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 06: Verify 'My account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkIsDisplayed());
	}

	@Test
	public void Sort_01_Name_A_To_Z() {
		log.info("Sort_01 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_01 - Step 03: Select product sort order: A to Z");
		homePage.selectToSortbyDropdown("Name: A to Z");

		log.info("Sort_01 - Step 04: Verify product name sort by: A to Z");
		verifyTrue(homePage.isProductNameSortAscending());
	}

	// Test
	public void Sort_02_Name_Z_To_A() {
		log.info("Sort_02 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_02 - Step 03: Select product sort order: Z to A");
		homePage.selectToSortbyDropdown("Name: Z to A");

		log.info("Sort_02 - Step 04: Verify product name sort by: Z to A");
		verifyTrue(homePage.isProductNameSortDescending());
	}

	@Test
	public void Sort_03_Price_Low_To_High() {
		log.info("Sort_03 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_03 - Step 03: Select product sort order: Low to High");
		homePage.selectToSortbyDropdown("Price: Low to High");

		log.info("Sort_03 - Step 04: Verify product name sort by: Low to High");
		verifyTrue(homePage.isProductPriceSortAscending());
	}

	@Test
	public void Sort_04_Price_High_To_Low() {
		log.info("Sort_04 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_04 - Step 03: Select product sort order: High to Low");
		homePage.selectToSortbyDropdown("Price: High to Low");

		log.info("Sort_04 - Step 04: Verify product name sort by: High to Low");
		verifyTrue(homePage.isProductPriceSortDescending());
	}

	@Test
	public void Sort_05_Display_With_3_Product() {
		log.info("Sort_05 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_05 - Step 02: Select value ='3' to 'Display' dropdown");
		homePage.selectToDisplayDropdown("3");

		log.info("Sort_05 - Step 03: Verify only has exactly 3 or less than 3 products");
		verifyTrue(homePage.isLessThanOr3Product());

		log.info("Sort_05 - Step 04: Verify 'Next icon' appear");
		verifyTrue(homePage.isNextIconDiplayed());

		log.info("Sort_05 - Step 05: Click to 'Next icon'");
		homePage.clickToNextIcon();

		log.info("Sort_05 - Step 06: Verify 'Previous' appear");
		verifyTrue(homePage.isPreviousIconDiplayed());
	}

	@Test
	public void Sort_06_Display_With_6_Product() {
		log.info("Sort_06 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_06 - Step 02: Select value ='6' to 'Display' dropdown");
		homePage.selectToDisplayDropdown("6");

		log.info("Sort_06 - Step 03: Verify only has exactly 6 or less than 6 products");
		verifyTrue(homePage.isLessThanOr6Product());

		log.info("Sort_06 - Step 04: Verify 'Next icon' dissappear");
		verifyTrue(homePage.isNextIconNotDiplayed());
	}

	@Test
	public void Sort_07_Display_With_9_Product() {
		log.info("Sort_07 - Step 01: Open product in submenu page");
		homePage.openSubmenuPage(driver, "Computers ", "Notebooks ");

		log.info("Sort_07 - Step 02: Select value ='9' to 'Display' dropdown");
		homePage.selectToDisplayDropdown("9");

		log.info("Sort_07 - Step 03: Verify only has exactly 9 or less than 9 products");
		verifyTrue(homePage.isLessThanOr9Product());

		log.info("Sort_07 - Step 04: Verify 'Next icon' dissappear");
		verifyTrue(homePage.isNextIconNotDiplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
