package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGenerator;
import pageObjects.nopComerce.user.UserAddressesPageObject;
import pageObjects.nopComerce.user.UserChangePasswordPageObject;
import pageObjects.nopComerce.user.UserCustomerInfoPageObject;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserLoginPageObject;
import pageObjects.nopComerce.user.UserMyProductReviewPageObject;
import pageObjects.nopComerce.user.UserRegisterPageObject;

public class User_03_My_Account extends BaseTest {

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeClass()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FA";
		password = "12345678";
		email = "afc" + getRandomNumber() + "@gmail.com";

		editFirstName = "Testing";
		editLastName = "VN";
		day = "1";
		month = "January";
		year = "1999";
		editEmail = "auto" + getRandomNumber() + "@gmail.com";
		companyName = "Automation FF";

		addressFirstName = "Automation";
		addressLastName = "FC";
		addressEmail = "kimbum" + getRandomNumber() + "@gmail.com";
		addressCompany = "Automation FA";
		addressCountryID = "Viet Nam";
		addressCity = "Da Nang";
		addressAddress1 = "123/04 Le Lai";
		addressAddress2 = "234/05 Hai Phong";
		addressPostalCode = "65000";
		addressPhoneNumber = "0644447777";
		addressFaxNumber = "0987654321";
		newPassword = "12345678910";
		int idProduct = getRandomNumber();
		reviewTitle = "Order Success " + idProduct;
		reviewText = "Good Product " + idProduct;

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

		log.info("Pre-condition - Step 10: Open Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Pre-condition - Step 11: Input existing email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", email);

		log.info("Pre-condition - Step 12: Input password into 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 13: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 14: Verify 'My Account' link displayed");
		verifyTrue(homePage.isMyAccountLinkIsDisplayed());

	}

	@Test
	public void My_Account_01_Customer_Infor() {
		log.info("My_Account_01 - Step 01: click to 'My Account' link");
		homePage.openPageAtHeader(driver, "ico-account");
		customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);

		log.info("My_Account_01 - Step 02: Verify 'My account' link is displayed");
		verifyTrue(customerInfoPage.isCustomerInfoPageIsDisplayed());

		log.info("My_Account_01 - Step 03: Update info Customer");
		customerInfoPage.clickToRadioFemaleGender();

		log.info("My_Account_01 - Step 04: Enter to 'First Name' textbox");
		customerInfoPage.inputToTextboxByID(driver, "FirstName", editFirstName);

		log.info("My_Account_01 - Step 05: Enter to 'Last Name' textbox");
		customerInfoPage.inputToTextboxByID(driver, "LastName", editLastName);

		log.info("My_Account_01 - Step 06: Select to 'Date of birth' dropdown");
		customerInfoPage.selectItemInDropDownByName(driver, "DateOfBirthDay", day);
		customerInfoPage.selectItemInDropDownByName(driver, "DateOfBirthMonth", month);
		customerInfoPage.selectItemInDropDownByName(driver, "DateOfBirthYear", year);

		log.info("My_Account_01 - Step 07: Enter to 'Email' textbox");
		customerInfoPage.inputToTextboxByID(driver, "Email", editEmail);

		log.info("My_Account_01 - Step 08: Enter to 'Company Name' textbox");
		customerInfoPage.inputToTextboxByID(driver, "Company", companyName);

		log.info("My_Account_01 - Step 09: Click to 'Save' button");
		customerInfoPage.clickToButtonByClass(driver, "save-customer-info-button");

		log.info("My_Account_01 - Step 10: Verify infor updated success");
		verifyTrue(customerInfoPage.isFemaleRadioSelected());
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), editFirstName);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), editLastName);
		verifyEquals(customerInfoPage.getSelectedValueInDropDownByName(driver, "DateOfBirthDay"), day);
		verifyEquals(customerInfoPage.getSelectedValueInDropDownByName(driver, "DateOfBirthMonth"), month);
		verifyEquals(customerInfoPage.getSelectedValueInDropDownByName(driver, "DateOfBirthYear"), year);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), editEmail);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "Company"), companyName);
	}

	@Test
	public void My_Account_02_Addresses() {
		log.info("My_Account_02 - Step 01: Open 'Addresses' page");
		customerInfoPage.openPageAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGenerator.getUserAddressPage(driver);

		log.info("My_Account_02 - Step 02: Click to 'Add new' button");
		addressPage.clickToButtonByClass(driver, "button-1 add-address-button");

		log.info("My_Account_02 - Step 03: Enter to 'First Name' textbox");
		addressPage.inputToTextboxByID(driver, "Address_FirstName", addressFirstName);

		log.info("My_Account_02 - Step 04: Enter to 'Last Name' textbox");
		addressPage.inputToTextboxByID(driver, "Address_LastName", addressLastName);

		log.info("My_Account_02 - Step 05: Enter to 'Email' textbox");
		addressPage.inputToTextboxByID(driver, "Address_Email", addressEmail);

		log.info("My_Account_02 - Step 06: Enter to 'Company' textbox");
		addressPage.inputToTextboxByID(driver, "Address_Company", addressCompany);

		log.info("My_Account_02 - Step 07: Select to 'Country' dropdown");
		addressPage.selectToCountryDropdown(addressCountryID);

		log.info("My_Account_02 - Step 09: Enter to 'City' textbox");
		addressPage.inputToTextboxByID(driver, "Address_City", addressCity);

		log.info("My_Account_02 - Step 10: Enter to 'Address1' textbox");
		addressPage.inputToTextboxByID(driver, "Address_Address1", addressAddress1);

		log.info("My_Account_02 - Step 11: Enter to 'Address2' textbox");
		addressPage.inputToTextboxByID(driver, "Address_Address2", addressAddress2);

		log.info("My_Account_02 - Step 12: Enter to 'Zip/postal code' textbox");
		addressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", addressPostalCode);

		log.info("My_Account_02 - Step 13: Enter to 'Phone number' textbox");
		addressPage.inputToTextboxByID(driver, "Address_PhoneNumber", addressPhoneNumber);

		log.info("My_Account_02 - Step 14: Enter to 'Fax number' textbox");
		addressPage.inputToTextboxByID(driver, "Address_FaxNumber", addressFaxNumber);

		log.info("My_Account_02 - Step 15: Click to 'Save' button");
		addressPage.clickToButtonByClass(driver, "save-address-button");

		log.info("My_Account_02 - Step 16: Verify info update success");
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "name"), addressFirstName + " " + addressLastName);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "email"), "Email: " + addressEmail);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "phone"), "Phone number: " + addressPhoneNumber);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "fax"), "Fax number: " + addressFaxNumber);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "company"), addressCompany);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "address1"), addressAddress1);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "address2"), addressAddress2);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "city-state-zip"), addressCity + ", " + addressPostalCode);
		verifyEquals(addressPage.getTextAtTextboxByClass(driver, "country"), addressCountryID);
	}

	@Test
	public void My_Account_03_Change_Password() {
		log.info("My_Account_03 - Step 01: Open 'Change password' page");
		addressPage.openPageAtMyAccountByPageName(driver, "Change password");
		changePasswordPage = PageGenerator.getChangePasswordPage(driver);

		log.info("My_Account_03 - Step 02: Enter to 'Old password' textbox");
		changePasswordPage.inputToTextboxByID(driver, "OldPassword", password);

		log.info("My_Account_03 - Step 03: Enter to 'New password' textbox");
		changePasswordPage.inputToTextboxByID(driver, "NewPassword", newPassword);

		log.info("My_Account_03 - Step 04: Enter to 'Confirm password' textbox");
		changePasswordPage.inputToTextboxByID(driver, "ConfirmNewPassword", newPassword);

		log.info("My_Account_03 - Step 05: Click to 'Change password' button");
		changePasswordPage.clickToButtonByClass(driver, "change-password-button");

		log.info("My_Account_03 - Step 06: Verify success message at bar notification");
		verifyEquals(changePasswordPage.getMessageInBarNotification(driver), "Password was changed");

		log.info("My_Account_03 - Step 07: Close bar notification");
		changePasswordPage.closeBarNotification(driver);

		log.info("My_Account_03 - Step 08: Click to 'Logout' link");
		registerPage.openPageAtHeader(driver, "ico-logout");
		homePage = PageGenerator.getUserHomePage(driver);

		log.info("My_Account_03 - Step 09: Click to 'Login' link");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("My_Account_03 - Step 10: Enter existing email to 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", editEmail);

		log.info("My_Account_03 - Step 11: Enter old password to 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("My_Account_03 - Step 12: Click 'Login' button");
		loginPage.clickToLoginButton();

		log.info("My_Account_03 - Step 13: Verify error message is display");
		verifyEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		log.info("My_Account_03 - Step 14: Click to 'Login' link");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("My_Account_03 - Step 15: Enter existing email to 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", editEmail);

		log.info("My_Account_03 - Step 16: Enter new password to 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", newPassword);

		log.info("My_Account_03 - Step 17: Click 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("My_Account_03 - Step 18: Verify 'My account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkIsDisplayed());
	}

	@Test
	public void My_Account_04_My_Product_Review() {
		log.info("My_Account_04 - Step 01: Click to any product");
		homePage.openSubmenuPage(driver, "Computers ", "Desktops ");
		homePage.clickToAnyProduct(driver, "Build your own computer");

		log.info("My_Account_04 - Step 02: Click to 'Add your review' link");
		homePage.clickToAddYourReview();

		log.info("My_Account_04 - Step 03: Enter value to 'Review title' textbox");
		homePage.inputToTextboxByID(driver, "AddProductReview_Title", reviewTitle);

		log.info("My_Account_04 - Step 04: Enter value to 'Review text' textbox");
		homePage.inputToTextareaReviewText(reviewText);

		log.info("My_Account_04 - Step 05: Click to 'Rating' radio");
		homePage.clickToRadioButtonByID(driver, "addproductrating_4");

		log.info("My_Account_04 - Step 06: Click to 'Submit review' button");
		homePage.clickToButtonByClass(driver, "write-product-review-button");
		myProductReviewPage = PageGenerator.getUserMyProductReviewPage(driver);

		log.info("My_Account_04 - Step 07: Verify review product add success ");
		verifyEquals(myProductReviewPage.getSuccessMessage(), "Product review is successfully added.");
		verifyEquals(myProductReviewPage.getReviewTitle(), reviewTitle);
		verifyEquals(myProductReviewPage.getReviewText(), reviewText);

		log.info("My_Account_04 - Step 08: Open 'My account' page");
		myProductReviewPage.openPageAtHeader(driver, "ico-account");
		customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);

		log.info("My_Account_04 - Step 09: Open 'My product reviews' page");
		customerInfoPage.openPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGenerator.getUserMyProductReviewPage(driver);

		log.info("My_Account_04 - Step 10: Verify review is displayed");
		verifyEquals(myProductReviewPage.getReviewTitle(), reviewTitle);
		verifyEquals(myProductReviewPage.getReviewText(), reviewText);

		log.info("My_Account_04 - Step 11: Click to product link");
		homePage = myProductReviewPage.clickToProductLink();

		log.info("My_Account_04 - Step 12: Click to product reviews link");
		myProductReviewPage = homePage.clickToProductReviewLink();

		log.info("My_Account_04 - Step 13: Verify review is displayed");
		verifyEquals(myProductReviewPage.getReviewTitle(), reviewTitle);
		verifyEquals(myProductReviewPage.getReviewText(), reviewText);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private UserRegisterPageObject registerPage;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserAddressesPageObject addressPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private String firstName, lastName, email, password, editFirstName, editLastName, day, month, year, editEmail, companyName;
	private String addressFirstName, addressLastName, addressEmail, addressCompany, addressCountryID, addressCity, addressAddress1, addressAddress2, addressPostalCode, addressPhoneNumber,
			addressFaxNumber;
	private String newPassword, reviewTitle, reviewText;
}
