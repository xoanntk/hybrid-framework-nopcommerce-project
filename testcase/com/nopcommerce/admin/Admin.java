package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGenerator;
import pageObject.nopComerce.admin.AdminAddNewAddressPageObject;
import pageObject.nopComerce.admin.AdminAddNewCusomerPageObject;
import pageObject.nopComerce.admin.AdminCustomersPageObject;
import pageObject.nopComerce.admin.AdminDashboardPageObject;
import pageObject.nopComerce.admin.AdminEditAddressPageObject;
import pageObject.nopComerce.admin.AdminEditCusomerPageObject;
import pageObject.nopComerce.admin.AdminEditProductDetailPageObject;
import pageObject.nopComerce.admin.AdminLoginPageObject;
import pageObject.nopComerce.admin.AdminProductsPageObject;

public class Admin extends BaseTest {

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeTest()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		loginPage = PageGenerator.getAdminLoginPage(driver);

		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		email = "automationfc" + getRandomNumber() + "@gmail.com";
		password = "123456";
		firstName = "Nobita";
		lastName = "Xuka";
		name = firstName + " " + lastName;
		dateOfBirth = "1/1/2000";
		companyName = "Automation FC";
		customerRoles = "Guests";
		adminComment = "Add new Customer (Guest)";

		editEmail = "editautomationfc" + getRandomNumber() + "@gmail.com";
		editFirstName = "EditNobita";
		editLastName = "EditXuka";
		editName = editFirstName + " " + editLastName;
		editDateOfBirth = "2/2/2008";
		editCompany = "EditAutomation FC";
		editAdminComment = "Edit Add new Customer (Guest)";

		addressFirstName = "Romeo";
		addressLastName = "Juliet";
		addressEmail = "romeo" + getRandomNumber() + "@gmail.com";
		addressCompany = "Automation FC";
		addressCountry = "Viet Nam";
		addressCity = "Ho Chi Minh";
		addressAddress1 = "743 Le Loi";
		addressAddress2 = "453 Le Lai";
		addressZipPostalcode = "65000";
		addressPhoneNumber = "0665555757";
		addressFaxNumber = "+84866776977";

		editAddressFirstName = "Chi";
		editAddressLastName = "Pu";
		editAddressEmail = "chipu" + getRandomNumber() + "@gmail.com";
		editAddressCompany = "Automation FC";
		editAddressCountry = "Viet Nam";
		editAddressCity = "Ha Noi";
		editAddressAddress1 = "113 Le Loi";
		editAddressAddress2 = "456 Le Lai";
		editAddressZipPostalcode = "55000";
		editAddressPhoneNumber = "0665555700";
		editAddressFaxNumber = "+84866776999";

		log.info("Pre-Conditon - Step 02: Login as admin");
		dashboardPage = loginPage.loginAsAdmin("admin@yourstore.com", "admin");

		log.info("Pre-Conditon - Step 03: Verify dashboard header is displayed");
		Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());
	}

	@Test
	public void Admin_01_Search_With_ProductName() {
		log.info("Admin_01 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "fas fa-book", "Products");
		productPage = PageGenerator.getProductPage(driver);

		log.info("Admin_01 - Step 02: Input to 'Product Name' textbox");
		productPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_01 - Step 03: Click to 'Search' button");
		productPage.clickToButtonByID(driver, "search-products");

		log.info("Admin_01 - Step 04: Verify only 1 item is displayed");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		verifyEquals(productPage.getTotalNumberProductDisplay(), 1);
		verifyTrue(productPage.isProductsDiplayed(productName, "LE_IC_600", "500", "10000", "true"));
	}

	@Test
	public void Admin_02_Search_With_ProductName_ParentCatefory_Uncheck() {
		log.info("Admin_02 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "fas fa-book", "Products");
		productPage = PageGenerator.getProductPage(driver);

		log.info("Admin_02 - Step 02: Input to 'Product Name' textbox");
		productPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_02 - Step 03: Select 'Computers' to 'Category' dropdown");
		productPage.selectItemInDropDownByName(driver, "SearchCategoryId", "Computers");

		log.info("Admin_02 - Step 04: Uncheck to 'Search subcategories' checkbox");
		productPage.unCheckToSearchSubcategoryCheckbox();

		log.info("Admin_02 - Step 05: Click to 'Search' button");
		productPage.clickToButtonByID(driver, "search-products");

		log.info("Admin_02 - Step 06: Verify message is displayed");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		verifyTrue(productPage.isNoDataMessageDisplayed());
	}

	@Test
	public void Admin_03_Search_With_ProductName_ParentCatefory_Check() {
		log.info("Admin_03 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "fas fa-book", "Products");
		productPage = PageGenerator.getProductPage(driver);

		log.info("Admin_03 - Step 02: Input to 'Product Name' textbox");
		productPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_03 - Step 03: Select 'Computers' to 'Category' dropdown");
		productPage.selectItemInDropDownByName(driver, "SearchCategoryId", "Computers");

		log.info("Admin_03 - Step 04: Check to 'Search subcategories' checkbox");
		productPage.checkToSearchSubcategoryCheckbox();

		log.info("Admin_03 - Step 05: Click to 'Search' button");
		productPage.clickToButtonByID(driver, "search-products");

		log.info("Admin_03 - Step 06: Verify message is displayed");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		verifyEquals(productPage.getTotalNumberProductDisplay(), 1);
		verifyTrue(productPage.isProductsDiplayed(productName, "LE_IC_600", "500", "10000", "true"));
	}

	@Test
	public void Admin_04_Search_With_ProductName_ChildCategory() {
		log.info("Admin_04 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "fas fa-book", "Products");
		productPage = PageGenerator.getProductPage(driver);

		log.info("Admin_04 - Step 02: Input to 'Product Name' textbox");
		productPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_04 - Step 03: Select 'Computers' to 'Category' dropdown");
		productPage.selectItemInDropDownByName(driver, "SearchCategoryId", "Computers >> Desktops");

		log.info("Admin_04 - Step 04: Uncheck to 'Search subcategories' checkbox");
		productPage.unCheckToSearchSubcategoryCheckbox();

		log.info("Admin_04 - Step 05: Click to 'Search' button");
		productPage.clickToButtonByID(driver, "search-products");

		log.info("Admin_04 - Step 06: Verify message is displayed");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		verifyEquals(productPage.getTotalNumberProductDisplay(), 1);
		verifyTrue(productPage.isProductsDiplayed(productName, "LE_IC_600", "500", "10000", "true"));
	}

	@Test
	public void Admin_05_Search_With_ProductName_Manufacturer() {
		log.info("Admin_05 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "fas fa-book", "Products");
		productPage = PageGenerator.getProductPage(driver);

		log.info("Admin_05 - Step 02: Input to 'Product Name' textbox");
		productPage.inputToTextboxByID(driver, "SearchProductName", productName);

		log.info("Admin_05 - Step 03: Select 'Computers' to 'Category' dropdown");
		productPage.selectItemInDropDownByName(driver, "SearchCategoryId", "All");

		log.info("Admin_05 - Step 04: Uncheck to 'Search subcategories' checkbox");
		productPage.unCheckToSearchSubcategoryCheckbox();

		log.info("Admin_05 - Step 05: Select 'Apple' to 'Manufacturer' dropdown");
		productPage.selectItemInDropDownByName(driver, "SearchManufacturerId", "Apple");

		log.info("Admin_05 - Step 06: Click to 'Search' button");
		productPage.clickToButtonByID(driver, "search-products");

		log.info("Admin_05 - Step 07: Verify message is displayed");
		verifyTrue(productPage.isJQueryAjaxLoadedSuccess(driver));
		verifyTrue(productPage.isNoDataMessageDisplayed());
	}

	@Test
	public void Admin_06_Go_Directly_to_product_SKU() {
		log.info("Admin_06 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "fas fa-book", "Products");
		productPage = PageGenerator.getProductPage(driver);

		log.info("Admin_06 - Step 02: Input to 'Go directly to product SKU' textbox");
		productPage.inputToTextboxByID(driver, "GoDirectlyToSku", "LE_IC_600");

		log.info("Admin_06 - Step 03: Click to 'Go' button");
		productPage.clickToButtonByID(driver, "go-to-product-by-sku");
		editProductDetailPage = PageGenerator.getEditProductDetailPage(driver);

		log.info("Admin_06 - Step 04: Verify info product is displayed");
		editProductDetailPage.clickToProductInfoCard();
		verifyTrue(editProductDetailPage.getProductNameDisplayed().equals(productName));
	}

	@Test
	public void Admin_07_Create_New_Customer() {
		log.info("Admin_07 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_07 - Step 02: Click to 'Add new' button");
		addNewCustomerPage = customersPage.clickToAddNewButton();

		log.info("Admin_07 - Step 03: Enter valid data at textboxes");
		addNewCustomerPage.inputToTextboxByID(driver, "Email", email);
		addNewCustomerPage.inputToTextboxByID(driver, "Password", password);
		addNewCustomerPage.inputToTextboxByID(driver, "FirstName", firstName);
		addNewCustomerPage.inputToTextboxByID(driver, "LastName", lastName);
		addNewCustomerPage.clickToRadioButtonByID(driver, "Gender_Male");
		addNewCustomerPage.inputToTextboxByID(driver, "DateOfBirth", dateOfBirth);
		addNewCustomerPage.inputToTextboxByID(driver, "Company", companyName);
		addNewCustomerPage.inputToCustomerRolesListbox(customerRoles);
		addNewCustomerPage.checkToActiveCheckbox();
		addNewCustomerPage.inputToAdminCommentTextArea(adminComment);

		log.info("Admin_07 - Step 04: Click to 'Save and Continute Edit' button");
		addNewCustomerPage.clickToSaveAndContinuteEditButton();

		log.info("Admin_07 - Step 05: Verify success message is displayed");
		verifyTrue(addNewCustomerPage.getSuccessMessageDisplayed().contains("The new customer has been added successfully."));
		verifyEquals(addNewCustomerPage.getTextboxValueByID(driver, "Email"), email);
		verifyEquals(addNewCustomerPage.getTextboxValueByID(driver, "FirstName"), firstName);
		verifyEquals(addNewCustomerPage.getTextboxValueByID(driver, "LastName"), lastName);
		verifyTrue(addNewCustomerPage.isRadioCheckboxSelectedByID(driver, "Gender_Male"));
		verifyEquals(addNewCustomerPage.getTextboxValueByID(driver, "DateOfBirth"), dateOfBirth);
		verifyEquals(addNewCustomerPage.getTextboxValueByID(driver, "Company"), companyName);
		verifyTrue(addNewCustomerPage.isCustomerRolesSelected(customerRoles));
		verifyTrue(addNewCustomerPage.isRadioCheckboxSelectedByID(driver, "Active"));
		verifyEquals(addNewCustomerPage.getTextAtAdminCommentTextarea(), adminComment);

		log.info("Admin_07 - Step 06: Click to 'Back to customer list'");
		customersPage = addNewCustomerPage.clickToBackToCustomerList();

		log.info("Admin_07 - Step 07: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_07 - Step 08: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_07 - Step 09: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(name, customerRoles, companyName, "true"));
	}

	@Test
	public void Admin_08_Search_Customer_With_Email() {
		log.info("Admin_08 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_08 - Step 02: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", email);

		log.info("Admin_08 - Step 03: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_08 - Step 04: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_08 - Step 05: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(name, customerRoles, companyName, "true"));
	}

	@Test
	public void Admin_09_Search_Customer_With_FirstName_And_LastName() {
		log.info("Admin_09 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_09 - Step 02: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", firstName);

		log.info("Admin_09 - Step 03: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", lastName);

		log.info("Admin_09 - Step 04: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_09 - Step 05: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_09 - Step 06: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(name, customerRoles, companyName, "true"));
	}

	@Test
	public void Admin_10_Search_Customer_With_Company() {
		log.info("Admin_10 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_10 - Step 02: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", companyName);

		log.info("Admin_10 - Step 03: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_10 - Step 04: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_10 - Step 05: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(name, customerRoles, companyName, "true"));
	}

	@Test
	public void Admin_11_Search_Customer_With_Full_Data() {
		log.info("Admin_11 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_11 - Step 02: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", email);

		log.info("Admin_11 - Step 03: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", firstName);

		log.info("Admin_11 - Step 04: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", lastName);

		log.info("Admin_11 - Step 05: Select to 'Date of Birth' dropdown");
		customersPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", "1");
		customersPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", "1");

		log.info("Admin_11 - Step 06: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", companyName);

		log.info("Admin_11 - Step 07: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_11 - Step 08: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_11 - Step 09: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(name, customerRoles, companyName, "true"));
	}

	@Test
	public void Admin_12_Edit_Customer() {
		log.info("Admin_12 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_12 - Step 02: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", email);

		log.info("Admin_12 - Step 03: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", firstName);

		log.info("Admin_12 - Step 04: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", lastName);

		log.info("Admin_12 - Step 05: Select to 'Date of Birth' dropdown");
		customersPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", "1");
		customersPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", "1");

		log.info("Admin_12 - Step 06: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", companyName);

		log.info("Admin_12 - Step 07: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_12 - Step 08: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_12 - Step 09: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(name, customerRoles, companyName, "true"));

		log.info("Admin_12 - Step 10: Click to 'Edit' button");
		editCustomersPage = customersPage.clickToEditButton();

		log.info("Admin_12 - Step 11: Enter valid data at textboxes");
		editCustomersPage.inputToTextboxByID(driver, "Email", editEmail);
		editCustomersPage.inputToTextboxByID(driver, "FirstName", editFirstName);
		editCustomersPage.inputToTextboxByID(driver, "LastName", editLastName);
		editCustomersPage.inputToTextboxByID(driver, "DateOfBirth", editDateOfBirth);
		editCustomersPage.inputToTextboxByID(driver, "Company", editCompany);
		editCustomersPage.inputToAdminCommentTextArea(editAdminComment);

		log.info("Admin_12 - Step 12: Click to 'Save' button");
		customersPage = editCustomersPage.clickToSaveButton();

		log.info("Admin_12 - Step 13: Verify success message is displayed");
		verifyTrue(customersPage.getSuccessMessageDisplayed().contains("The customer has been updated successfully."));

		log.info("Admin_12 - Step 14: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", editEmail);

		log.info("Admin_12 - Step 15: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_12 - Step 16: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_12 - Step 17: Select to 'Date of Birth' dropdown");
		customersPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", "2");
		customersPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_12 - Step 18: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", editCompany);

		log.info("Admin_12 - Step 19: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_12 - Step 20: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_12 - Step 21: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(editName, customerRoles, editCompany, "true"));
	}

	@Test
	public void Admin_13_Add_New_Address_In_Customer_Detail() {
		log.info("Admin_13 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_13 - Step 02: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", editEmail);

		log.info("Admin_13 - Step 03: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_13 - Step 04: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_13 - Step 05: Select to 'Date of Birth' dropdown");
		customersPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", "2");
		customersPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_13 - Step 06: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", editCompany);

		log.info("Admin_13 - Step 07: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_13 - Step 08: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_13 - Step 09: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(editName, customerRoles, editCompany, "true"));

		log.info("Admin_13 - Step 10: Click to 'Edit' button");
		editCustomersPage = customersPage.clickToEditButton();

		log.info("Admin_13 - Step 11: Click to 'Addresses' card");
		verifyTrue(editCustomersPage.isJQueryAjaxLoadedSuccess(driver));
		editCustomersPage.clickToAddressesCard();

		log.info("Admin_13 - Step 12: Click to 'Add new address' button");
		addNewAddressPage = editCustomersPage.clickToAddNewAddressButton();

		log.info("Admin_13 - Step 13: Input valid data into fields");
		addNewAddressPage.inputToTextboxByID(driver, "Address_FirstName", addressFirstName);
		addNewAddressPage.inputToTextboxByID(driver, "Address_LastName", addressLastName);
		addNewAddressPage.inputToTextboxByID(driver, "Address_Email", addressEmail);
		addNewAddressPage.inputToTextboxByID(driver, "Address_Company", addressCompany);
		addNewAddressPage.selectItemInDropDownByName(driver, "Address.CountryId", addressCountry);
		addNewAddressPage.inputToTextboxByID(driver, "Address_City", addressCity);
		addNewAddressPage.inputToTextboxByID(driver, "Address_Address1", addressAddress1);
		addNewAddressPage.inputToTextboxByID(driver, "Address_Address2", addressAddress2);
		addNewAddressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", addressZipPostalcode);
		addNewAddressPage.inputToTextboxByID(driver, "Address_PhoneNumber", addressPhoneNumber);
		addNewAddressPage.inputToTextboxByID(driver, "Address_FaxNumber", addressFaxNumber);

		log.info("Admin_13 - Step 14: Click to 'Save' button");
		editAddressPage = addNewAddressPage.clickToSaveButton();

		log.info("Admin_13 - Step 15: Verify success message is displayed");
		verifyTrue(editAddressPage.getSuccessMessageDisplayed().contains("The new address has been added successfully."));

		log.info("Admin_13 - Step 16: Verify address added successfully ");
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_FirstName"), addressFirstName);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_LastName"), addressLastName);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Email"), addressEmail);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Company"), addressCompany);
		verifyEquals(editAddressPage.getSelectedValueInDropDownByName(driver, "Address.CountryId"), addressCountry);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Address1"), addressAddress1);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Address2"), addressAddress2);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_ZipPostalCode"), addressZipPostalcode);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_PhoneNumber"), addressPhoneNumber);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_FaxNumber"), addressFaxNumber);

		log.info("Admin_13 - Step 17: Click 'back to customer detail' link");
		editCustomersPage = editAddressPage.clickBackToCustomerDetailLink();

		log.info("Admin_13 - Step 18: Click to 'Addresses' card");
		verifyTrue(editCustomersPage.isJQueryAjaxLoadedSuccess(driver));
		editCustomersPage.clickToAddressesCard();

		log.info("Admin_13 - Step 19: Verify address info is displayed ");
		editCustomersPage.isAddressDisplayed(addressFirstName, addressLastName, addressEmail, addressPhoneNumber, addressFaxNumber, addressCompany, addressAddress1, addressAddress2,
				addressCity + "," + addressZipPostalcode, addressCountry);
	}

	@Test
	public void Admin_14_Edit_Address_In_Customer_Detail() {
		log.info("Admin_14 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_14 - Step 02: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", editEmail);

		log.info("Admin_14 - Step 03: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_14 - Step 04: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_14 - Step 05: Select to 'Date of Birth' dropdown");
		customersPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", "2");
		customersPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_14 - Step 06: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", editCompany);

		log.info("Admin_14 - Step 07: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_14 - Step 08: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_14 - Step 09: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(editName, customerRoles, editCompany, "true"));

		log.info("Admin_14 - Step 10: Click to 'Edit' button");
		editCustomersPage = customersPage.clickToEditButton();

		log.info("Admin_14 - Step 11: Click to 'Addresses' card");
		verifyTrue(editCustomersPage.isJQueryAjaxLoadedSuccess(driver));
		editCustomersPage.clickToAddressesCard();

		log.info("Admin_14 - Step 12: Click to 'Edit' button");
		editAddressPage = editCustomersPage.clickToEditButton();
		verifyTrue(editAddressPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Admin_14 - Step 13: Enter valid data at textboxes");
		editAddressPage.inputToTextboxByID(driver, "Address_FirstName", editAddressFirstName);
		editAddressPage.inputToTextboxByID(driver, "Address_LastName", editAddressLastName);
		editAddressPage.inputToTextboxByID(driver, "Address_Email", editAddressEmail);
		editAddressPage.inputToTextboxByID(driver, "Address_Company", editAddressCompany);
		editAddressPage.selectItemInDropDownByName(driver, "Address.CountryId", editAddressCountry);
		editAddressPage.inputToTextboxByID(driver, "Address_City", editAddressCity);
		editAddressPage.inputToTextboxByID(driver, "Address_Address1", editAddressAddress1);
		editAddressPage.inputToTextboxByID(driver, "Address_Address2", editAddressAddress2);
		editAddressPage.inputToTextboxByID(driver, "Address_ZipPostalCode", editAddressZipPostalcode);
		editAddressPage.inputToTextboxByID(driver, "Address_PhoneNumber", editAddressPhoneNumber);
		editAddressPage.inputToTextboxByID(driver, "Address_FaxNumber", editAddressFaxNumber);

		log.info("Admin_14 - Step 14: Click to 'Save' button");
		editAddressPage.clickToSaveButton();

		log.info("Admin_14 - Step 15: Verify success message is displayed");
		verifyTrue(editAddressPage.getSuccessMessageDisplayed().contains("The address has been updated successfully."));

		log.info("Admin_14 - Step 16: Verify address added successfully ");
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_FirstName"), editAddressFirstName);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_LastName"), editAddressLastName);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Email"), editAddressEmail);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Company"), editAddressCompany);
		verifyEquals(editAddressPage.getSelectedValueInDropDownByName(driver, "Address.CountryId"), editAddressCountry);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Address1"), editAddressAddress1);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_Address2"), editAddressAddress2);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_ZipPostalCode"), editAddressZipPostalcode);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_PhoneNumber"), editAddressPhoneNumber);
		verifyEquals(editAddressPage.getTextboxValueByID(driver, "Address_FaxNumber"), editAddressFaxNumber);

		log.info("Admin_14 - Step 17: Click 'back to customer detail' link");
		editCustomersPage = editAddressPage.clickBackToCustomerDetailLink();

		log.info("Admin_14 - Step 18: Click to 'Addresses' card");
		verifyTrue(editCustomersPage.isJQueryAjaxLoadedSuccess(driver));
		editCustomersPage.clickToAddressesCard();

		log.info("Admin_14 - Step 19: Verify address info is displayed ");
		editCustomersPage.isAddressDisplayed(editAddressFirstName, editAddressLastName, editAddressEmail, editAddressPhoneNumber, editAddressFaxNumber, editAddressCompany, editAddressAddress1,
				editAddressAddress2, editAddressCity + "," + editAddressZipPostalcode, editAddressCountry);
	}

	@Test
	public void Admin_15_Delete_Address_In_Customer_Detail() {
		log.info("Admin_15 - Step 01: Open Products in Catalog");
		dashboardPage.openPageAtSideBarByClass(driver, "far fa-user", "Customers");
		customersPage = PageGenerator.getCustomersPage(driver);

		log.info("Admin_15 - Step 02: Enter to 'Email' textbox");
		customersPage.inputToTextboxByID(driver, "SearchEmail", editEmail);

		log.info("Admin_15 - Step 03: Enter to 'First Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchFirstName", editFirstName);

		log.info("Admin_15 - Step 04: Enter to 'Last Name' textbox");
		customersPage.inputToTextboxByID(driver, "SearchLastName", editLastName);

		log.info("Admin_15 - Step 05: Select to 'Date of Birth' dropdown");
		customersPage.selectItemInDropDownByName(driver, "SearchMonthOfBirth", "2");
		customersPage.selectItemInDropDownByName(driver, "SearchDayOfBirth", "2");

		log.info("Admin_15 - Step 06: Enter to 'Company' textbox");
		customersPage.inputToTextboxByID(driver, "SearchCompany", editCompany);

		log.info("Admin_15 - Step 07: Enter to 'CustomerRoles");
		customersPage.inputToCustomerRolesListbox(customerRoles);

		log.info("Admin_15 - Step 08: Click to 'Search' button");
		customersPage.clickToButtonByID(driver, "search-customers");

		log.info("Admin_15 - Step 09: Verify customer info");
		verifyTrue(customersPage.isCustomerDisplayed(editName, customerRoles, editCompany, "true"));

		log.info("Admin_15 - Step 10: Click to 'Edit' button");
		editCustomersPage = customersPage.clickToEditButton();

		log.info("Admin_15 - Step 11: Click to 'Addresses' card");
		verifyTrue(editCustomersPage.isJQueryAjaxLoadedSuccess(driver));
		editCustomersPage.clickToAddressesCard();

		log.info("Admin_15 - Step 12: Click to 'Delete' button");
		editCustomersPage.clickToDeleteButton();
		editCustomersPage.acceptToAlert();

		log.info("Admin_14 - Step 15: Verify success message is displayed");
		verifyTrue(editCustomersPage.isNoDataMessageDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private AdminLoginPageObject loginPage;
	private AdminProductsPageObject productPage;
	private AdminDashboardPageObject dashboardPage;
	private AdminCustomersPageObject customersPage;
	private AdminEditCusomerPageObject editCustomersPage;
	private AdminAddNewCusomerPageObject addNewCustomerPage;
	private AdminEditProductDetailPageObject editProductDetailPage;
	private AdminAddNewAddressPageObject addNewAddressPage;
	private AdminEditAddressPageObject editAddressPage;
	private String productName, email, password, firstName, lastName, name, dateOfBirth, companyName, customerRoles, adminComment;
	private String editEmail, editFirstName, editLastName, editName, editDateOfBirth, editCompany, editAdminComment;
	private String addressFirstName, addressLastName, addressEmail, addressCompany, addressCountry, addressCity, addressAddress1, addressAddress2, addressZipPostalcode, addressPhoneNumber,
			addressFaxNumber;
	private String editAddressFirstName, editAddressLastName, editAddressEmail, editAddressCompany, editAddressCountry, editAddressCity, editAddressAddress1, editAddressAddress2,
			editAddressZipPostalcode, editAddressPhoneNumber, editAddressFaxNumber;
}
