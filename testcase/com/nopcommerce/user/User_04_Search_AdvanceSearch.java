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
import pageObjects.nopComerce.user.UserSearchPageObject;

public class User_04_Search_AdvanceSearch extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserSearchPageObject searchPage;
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

		log.info("Pre-condition - Step 04: Input password into 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 05: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 06: Verify 'My account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkIsDisplayed());
	}

	@Test
	public void Search_01_Empty_Data() {
		log.info("Search_01 - Step 01: Open Search at footer");
		homePage.openPageAtFooter(driver, "Search");
		searchPage = PageGenerator.getSearchPage(driver);

		log.info("Search_01 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_01 - Step 03: Verify error message is displayed");
		verifyEquals(searchPage.getWarningMessageDiplayed(), "Search term minimum length is 3 characters");

	}

	@Test
	public void Search_02_Data_Not_Exist() {
		log.info("Search_02 - Step 01: Enter Data not existing to 'Search' textbox");
		searchPage.inputToSearchTextbox("Macbook Pro 2050");

		log.info("Search_02 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_01 - Step 03: Verify error message is displayed");
		verifyEquals(searchPage.getNoResultMessageDisplayed(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_03_ProductName_Relative() {
		log.info("Search_03 - Step 01: Enter Product Name relative to 'Search' textbox");
		searchPage.inputToSearchTextbox("Lenovo");

		log.info("Search_03 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_03 - Step 03: Verify products is displayed");
		verifyEquals(searchPage.getTotalNumberProductDisplay(), 2);
		verifyTrue(searchPage.isProductsDiplayed("Lenovo"));
	}

	@Test
	public void Search_04_ProductName_Absolute() {
		log.info("Search_04 - Step 01: Enter Product Name relative to 'Search' textbox");
		searchPage.inputToSearchTextbox("Lenovo Thinkpad X1 Carbon Laptop");

		log.info("Search_04 - Step 02: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_04 - Step 03: Verify products is displayed");
		verifyEquals(searchPage.getTotalNumberProductDisplay(), 1);
		verifyTrue(searchPage.isProductsDiplayed("Lenovo Thinkpad X1 Carbon Laptop"));
	}

	@Test
	public void Search_05_Parent_Categories() {
		log.info("Search_05 - Step 01: Enter Product Name relative to 'Search' textbox");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");

		log.info("Search_05 - Step 02: Check to 'Advance search' textbox");
		searchPage.checkToAdvanceSearchCheckbox();

		log.info("Search_05 - Step 03: Select value in 'Category' dropdown");
		searchPage.selectValueinCategoryDropDown("Computers");

		log.info("Search_05 - Step 04: Uncheck to 'Automatically search sub categories' checkbox");
		searchPage.uncheckToAutomateSubCategory();

		log.info("Search_05 - Step 05: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_05 - Step 06: Verify message is displayed");
		verifyEquals(searchPage.getNoResultMessageDisplayed(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_06_Sub_Categories() {
		log.info("Search_06 - Step 01: Enter Product Name relative to 'Search' textbox");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");

		log.info("Search_06 - Step 02: Check to 'Advance search' textbox");
		searchPage.checkToAdvanceSearchCheckbox();

		log.info("Search_06 - Step 03: Select value in 'Category' dropdown");
		searchPage.selectValueinCategoryDropDown("Computers");

		log.info("Search_06 - Step 04: Uncheck to 'Automatically search sub categories' checkbox");
		searchPage.checkToAutomateSubCategory();

		log.info("Search_06 - Step 05: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_06 - Step 06: Verify products is displayed");
		verifyEquals(searchPage.getTotalNumberProductDisplay(), 1);
		verifyTrue(searchPage.isProductsDiplayed("Apple MacBook Pro"));
	}

	@Test
	public void Search_07_Incorrect_Manufacturer() {
		log.info("Search_07 - Step 01: Enter Product Name relative to 'Search' textbox");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");

		log.info("Search_07 - Step 02: Check to 'Advance search' textbox");
		searchPage.checkToAdvanceSearchCheckbox();

		log.info("Search_07 - Step 03: Select value in 'Category' dropdown");
		searchPage.selectValueinCategoryDropDown("Computers");

		log.info("Search_07 - Step 04: Uncheck to 'Automatically search sub categories' checkbox");
		searchPage.checkToAutomateSubCategory();

		log.info("Search_07 - Step 05: Uncheck to 'Automatically search sub categories' checkbox");
		searchPage.selectValueinManufaturerDropDown("HP");

		log.info("Search_07 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_07 - Step 07: Verify message is displayed");
		verifyEquals(searchPage.getNoResultMessageDisplayed(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_Correct_Manufacturer() {
		log.info("Search_08 - Step 01: Enter Product Name relative to 'Search' textbox");
		searchPage.inputToSearchTextbox("Apple Macbook Pro");

		log.info("Search_08 - Step 02: Check to 'Advance search' textbox");
		searchPage.checkToAdvanceSearchCheckbox();

		log.info("Search_08 - Step 03: Select value in 'Category' dropdown");
		searchPage.selectValueinCategoryDropDown("Computers");

		log.info("Search_08 - Step 04: Uncheck to 'Automatically search sub categories' checkbox");
		searchPage.checkToAutomateSubCategory();

		log.info("Search_08 - Step 05: Uncheck to 'Automatically search sub categories' checkbox");
		searchPage.selectValueinManufaturerDropDown("Apple");

		log.info("Search_08 - Step 06: Click to 'Search' button");
		searchPage.clickToButtonByClass(driver, "search-button");

		log.info("Search_08 - Step 07: Verify products is displayed");
		verifyEquals(searchPage.getTotalNumberProductDisplay(), 1);
		verifyTrue(searchPage.isProductsDiplayed("Apple MacBook Pro 13-inch"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
