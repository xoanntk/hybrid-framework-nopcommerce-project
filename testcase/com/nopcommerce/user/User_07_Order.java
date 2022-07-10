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
import pageObjects.nopComerce.user.UserCheckoutPageObject;
import pageObjects.nopComerce.user.UserCustomerInfoPageObject;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserLoginPageObject;
import pageObjects.nopComerce.user.UserOrdersPageObject;
import pageObjects.nopComerce.user.UserShoppingCartPageObject;

public class User_07_Order extends BaseTest {

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeClass()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		email = Common_01_Register.email;
		password = Common_01_Register.password;
		fullName = Common_01_Register.fullName;

		productName = "Build your own computer";
		processor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ram = "8GB [+$60.00]";
		hdd = "400 GB [+$100.00]";
		os = "Vista Premium [+$60.00]";
		sofware1 = "Microsoft Office [+$50.00]";
		sofware2 = "Acrobat Reader [+$10.00]";
		sofware3 = "Total Commander [+$5.00]";
		editProcessor = "2.2 GHz Intel Pentium Dual-Core E2200";
		editRam = "4GB [+$20.00]";
		editHdd = "320 GB";
		editOS = "Vista Home [+$50.00]";
		editQuantity = "2";
		productPriceUpdate = "$1,320.00";

		productNameUpdate = "Lenovo IdeaCentre 600 All-in-One PC";
		productNameCheckout = "Apple MacBook Pro 13-inch";

		country = "Viet Nam";
		city = "Ho Chi MInh";
		address1 = "D1/A, 385 street, Tang Nhon Phu A ward, district 9";
		zipPostalCode = "688655";
		phoneNumber = "0777777766";

		creditCard = "Visa";
		cardHolderName = "Joshua White";
		cardNumber = "4389 0196 3492 0287";
		expireMonth = "03";
		expireYear = "2025";
		cardCode = "915";

		firstNameBilling = "Lee";
		lastNameBilling = "Minho";
		emailBilling = "leeminho" + getRandomNumber() + "@gmail.com";
		countryBilling = "Korea";
		cityBilling = "Seoul";
		address1Billing = "505/14 Ben Binh Dong Street, District 8";
		zipPostalCodeBilling = "646644";
		phoneNumberBilling = "0764646464";

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
	public void Order_01_Add_Product_To_Cart() {
		log.info("Order_01 - Step 01: Click to any product");
		homePage.openSubmenuPage(driver, "Computers ", "Desktops ");
		homePage.clickToAnyProduct(driver, productName);

		log.info("Order_01 - Step 02: Select to 'Processor' dropdown");
		homePage.selectToDropDownByName("product_attribute_1", processor);

		log.info("Order_01 - Step 03: Select to 'RAM' dropdown");
		homePage.selectToDropDownByName("product_attribute_2", ram);

		log.info("Order_01 - Step 04: Check to 'HDD' radio");
		homePage.checkToCheckboxRadioByName(hdd);

		log.info("Order_01 - Step 05: Check to 'OS' radio");
		homePage.checkToCheckboxRadioByName(os);

		log.info("Order_01 - Step 06: Check to 'Microsoft Office [+$50.00]' checkbox");
		homePage.checkToCheckboxRadioByName(sofware1);

		log.info("Order_01 - Step 07: Check to 'Acrobat Reader [+$10.00]' checkbox");
		homePage.checkToCheckboxRadioByName(sofware2);

		log.info("Order_01 - Step 08: Check to 'Total Commander [+$5.00]' checkbox");
		homePage.checkToCheckboxRadioByName(sofware3);

		productPrice = homePage.getProductPriceValue();
		quantity = homePage.getQuantityValue();

		log.info("Order_01 - Step 09: Click to 'Add to cart' button");
		homePage.clickToButtonByClass(driver, "add-to-cart-button");

		log.info("Order_01 - Step 10: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_01 - Step 11: Close bar notification");
		homePage.closeBarNotification(driver);

		log.info("Order_01 - Step 12: Verify quantity of product displayed in shopping cart header");
		verifyEquals(homePage.getCartQuantityOfProductAtShoppingCartHeader(), quantity);

		log.info("Order_01 - Step 13: Verify There are ? item(s) in your cart. is diplayed");
		verifyEquals(homePage.getCountItemsInShoppingCartHeader(), quantity);

		log.info("Order_01 - Step 14: Verify product is displayed in shopping cart header");
		verifyTrue(homePage.isProductNameDiplayedInShoppingCartHeader(productName));

		log.info("Order_01 - Step 15: Verify product information");
		verifyEquals(homePage.getInfoProduct(productName), "Processor: " + processor + "\n" + "RAM: " + ram + "\n" + "HDD: " + hdd + "\n" + "OS: " + os + "\n" + "Software: " + sofware1 + "\n"
				+ "Software: " + sofware2 + "\n" + "Software: " + sofware3);

		log.info("Order_01 - Step 16: Verify product price is displayed in shopping cart header");
		verifyEquals(homePage.getUnitPriceDiplayedInShoppingCartHeader(productName), productPrice);

		log.info("Order_01 - Step 17: Verify quantity is displayed in shopping cart header");
		verifyEquals(homePage.getQuantityDiplayedInShoppingCartHeader(productName), quantity);

		log.info("Order_01 - Step 18: Verify sub-total is displayed in shopping cart header");
		verifyEquals(homePage.getSubTotalInShoppingCartHeader(), productPrice);
	}

	@Test
	public void Order_02_Edit_Product_In_ShoppingCart() {
		log.info("Order_02 - Step 01: Open shopping cart");
		homePage.openPageAtHeader(driver, "ico-cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);

		log.info("Order_02 - Step 02: Click to edit link");
		homePage = shoppingCartPage.clickToEditLink();

		log.info("Order_02 - Step 03: Select to 'Processor' dropdown");
		homePage.selectToDropDownByName("product_attribute_1", editProcessor);

		log.info("Order_02 - Step 04: Select to 'RAM' dropdown");
		homePage.selectToDropDownByName("product_attribute_2", editRam);

		log.info("Order_02 - Step 05: Check to 'HDD' radio");
		homePage.checkToCheckboxRadioByName(editHdd);

		log.info("Order_02 - Step 06: Check to 'OS' radio");
		homePage.checkToCheckboxRadioByName(editOS);

		log.info("Order_02 - Step 07: Check to 'Microsoft Office [+$50.00]' checkbox");
		homePage.checkToCheckboxRadioByName(sofware1);

		log.info("Order_02 - Step 08: UnCheck to 'Acrobat Reader [+$10.00]' checkbox");
		homePage.unCheckToCheckboxRadioByName(sofware2);

		log.info("Order_02 - Step 09: UnCheck to 'Total Commander [+$5.00]' checkbox");
		homePage.unCheckToCheckboxRadioByName(sofware3);

		log.info("Order_02 - Step 10: Enter quantity to product");
		homePage.inputToQuantityProduct(editQuantity);

		log.info("Order_02 - Step 11: Verify product price is updated");
		verifyEquals(homePage.getProductPriceValue(), productPriceUpdate);

		productPrice = homePage.getProductPriceValue();
		quantity = homePage.getQuantityValue();

		log.info("Order_02 - Step 12: Click to 'Add to cart' button");
		homePage.clickToButtonByClass(driver, "add-to-cart-button");

		log.info("Order_02 - Step 13: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_02 - Step 14: Close bar notification");
		homePage.closeBarNotification(driver);

		log.info("Order_02 - Step 15: Open shopping cart");
		homePage.openPageAtHeader(driver, "ico-cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);

		log.info("Order_02 - Step 16: Verify product name is displayed in shopping cart page");
		verifyTrue(shoppingCartPage.isProductNameDiplayedInShoppingCartPage(productName));

		log.info("Order_02 - Step 17: Verify product information");
		verifyEquals(shoppingCartPage.getInfoProduct(productName),
				"Processor: " + editProcessor + "\n" + "RAM: " + editRam + "\n" + "HDD: " + editHdd + "\n" + "OS: " + editOS + "\n" + "Software: " + sofware1);

		log.info("Order_02 - Step 18: Verify product price is displayed in shopping cart page");
		verifyEquals(shoppingCartPage.getUnitPriceDiplayedInShoppingCartPage(productName), productPrice);

		log.info("Order_02 - Step 19: Verify quantity is displayed in shopping cart page");
		verifyEquals(shoppingCartPage.getQuantityDiplayedInShoppingCartPage(productName), quantity);

		log.info("Order_02 - Step 20: Verify sub-total is displayed in shopping cart page");
		verifyTrue(shoppingCartPage.isTotalInShoppingCartPageDisplayed(productName));
	}

	@Test
	public void Order_03_Remove_From_Cart() {
		log.info("Order_03 - Step 01: Click to 'remove' button");
		shoppingCartPage.clickToButtonByClass(driver, "remove-btn");

		log.info("Order_03 - Step 02: Verify message empty data is displayed");
		verifyEquals(shoppingCartPage.getNodataMessage(), "Your Shopping Cart is empty!");

		log.info("Order_03 - Step 03:Verify product is not display in shopping cart");
		verifyTrue(shoppingCartPage.isProductNameNotDiplayed(productName));
	}

	@Test
	public void Order_04_Update_ShoppingCart() {
		log.info("Order_04 - Step 01: Click to any product");
		shoppingCartPage.openSubmenuPage(driver, "Computers", "Desktops");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productNameUpdate);

		log.info("Order_04 - Step 02: Click to 'Add to cart' button");
		homePage.clickToButtonByClass(driver, "add-to-cart-button");

		log.info("Order_04 - Step 03: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_04 - Step 04: Close bar notification");
		homePage.closeBarNotification(driver);

		log.info("Order_04 - Step 05: Open shopping cart");
		homePage.openPageAtHeader(driver, "ico-cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);

		log.info("Order_04 - Step 06: Update quantity = 5");
		shoppingCartPage.inputToQuantiTyTextbox("5");

		log.info("Order_04 - Step 07: Click to 'Update Shopping Cart' button");
		shoppingCartPage.clickToButtonByID(driver, "updatecart");

		log.info("Order_04 - Step 08: Verify total increase $2,500.00");
		verifyTrue(shoppingCartPage.isTotalInShoppingCartPageDisplayed(productNameUpdate));

		log.info("Order_04 - Step 09: Click to 'remove' button");
		shoppingCartPage.clickToButtonByClass(driver, "remove-btn");
	}

	@Test
	public void Order_05_Checkout_Order_Payment_By_Cheque() {
		log.info("Order_05 - Step 01: Click to any product");
		shoppingCartPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productNameCheckout);

		productPrice = homePage.getProductPriceValue();
		quantity = homePage.getQuantityValue();

		log.info("Order_05 - Step 02: Click to 'Add to cart' button");
		homePage.clickToButtonByClass(driver, "add-to-cart-button");

		log.info("Order_05 - Step 03: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_05 - Step 04: Close bar notification");
		homePage.closeBarNotification(driver);

		log.info("Order_05 - Step 05: Open shopping cart");
		homePage.openPageAtHeader(driver, "ico-cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);

		log.info("Order_05 - Step 06: Click to Term checkbox");
		shoppingCartPage.clickToTermCheckbox();

		log.info("Order_05 - Step 07: Click to 'Checkout' button");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);

		log.info("Order_05 - Step 08: Select to 'Country' dropdown");
		checkoutPage.selectToCountryDropdown(country);

		log.info("Order_05 - Step 09: Enter to 'City' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_City", city);

		log.info("Order_05 - Step 10: Enter to 'Address1' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", address1);

		log.info("Order_05 - Step 11: Enter to 'Zip/postal code' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipPostalCode);

		log.info("Order_05 - Step 12: Enter to 'Phone number' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumber);

		log.info("Order_05 - Step 13: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "new-address-next-step-button");

		log.info("Order_05 - Step 14: Check to 'Ground' radio button");
		checkoutPage.clickToRadioButtonByID(driver, "shippingoption_0");

		log.info("Order_05 - Step 15: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "shipping-method-next-step-button");

		log.info("Order_05 - Step 16: Check to 'Check / Money Order' radio button");
		checkoutPage.clickToRadioButtonByID(driver, "paymentmethod_0");

		log.info("Order_05 - Step 17: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "payment-method-next-step-button");

		log.info("Order_05 - Step 18: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "payment-info-next-step-button");

		log.info("Order_05 - Step 19: Verify Billing Address information");
		verifyEquals(checkoutPage.getBillingInfoByClass("name"), fullName);
		verifyEquals(checkoutPage.getBillingInfoByClass("email"), "Email: " + email);
		verifyEquals(checkoutPage.getBillingInfoByClass("phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getBillingInfoByClass("address1"), address1);
		verifyEquals(checkoutPage.getBillingInfoByClass("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(checkoutPage.getBillingInfoByClass("country"), country);
		verifyEquals(checkoutPage.getPaymentIfo(), "Payment Method: Check / Money Order");

		log.info("Order_05 - Step 20: Verify Shipping Address information");
		verifyEquals(checkoutPage.getShippingInfoByClass("name"), fullName);
		verifyEquals(checkoutPage.getShippingInfoByClass("email"), "Email: " + email);
		verifyEquals(checkoutPage.getShippingInfoByClass("phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getShippingInfoByClass("address1"), address1);
		verifyEquals(checkoutPage.getShippingInfoByClass("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(checkoutPage.getShippingInfoByClass("country"), country);
		verifyEquals(checkoutPage.getShippingMethod(), "Shipping Method: Ground");

		log.info("Order_05 - Step 21: Verify Product is displayed");
		verifyTrue(checkoutPage.isProductNameDisplayed(productNameCheckout));

		log.info("Order_05 - Step 22: Verify Price is displayed");
		verifyEquals(checkoutPage.getUnitPrice(productNameCheckout), productPrice);

		log.info("Order_05 - Step 23: Verify Qty. is displayed");
		verifyEquals(checkoutPage.getQuantity(productNameCheckout), quantity);

		log.info("Order_05 - Step 24: Verify Total is displayed");
		verifyTrue(checkoutPage.isTotalDisplayed(productNameCheckout));
		String total = checkoutPage.getTotal(productNameCheckout);

		log.info("Order_05 - Step 25: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "confirm-order-next-step-button");

		log.info("Order_05 - Step 26: Verify success message and older number are displayed");
		verifyEquals(checkoutPage.getSuccessMessage(), "Your order has been successfully processed!");
		orderNumber = checkoutPage.getOlderNumberAtCheckoutPage();

		log.info("Order_05 - Step 27: Open 'My Account' page");
		checkoutPage.openPageAtHeader(driver, "ico-account");
		customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);

		log.info("Order_05 - Step 28: Open 'Orders' page");
		customerInfoPage.openPageAtMyAccountByPageName(driver, "Orders");
		ordersPage = PageGenerator.getOrdersPage(driver);

		log.info("Order_05 - Step 29: Verify order infomation");
		verifyTrue(ordersPage.isOrderNumberDisplayed(orderNumber));
		ordersPage.clickToDetail(orderNumber);
		verifyEquals(ordersPage.getOrderNumberAtOrdersDetail(), "ORDER #" + orderNumber);
		verifyEquals(ordersPage.getOrderStatus(), "Order Status: Pending");
		verifyEquals(ordersPage.getOrderTotalOverview(), "Order Total: " + total);

		verifyEquals(ordersPage.getBillingInfoByClassName("name"), fullName);
		verifyEquals(ordersPage.getBillingInfoByClassName("email"), "Email: " + email);
		verifyEquals(ordersPage.getBillingInfoByClassName("phone"), "Phone: " + phoneNumber);
		verifyEquals(ordersPage.getBillingInfoByClassName("address1"), address1);
		verifyEquals(ordersPage.getBillingInfoByClassName("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(ordersPage.getBillingInfoByClassName("country"), country);
		verifyEquals(ordersPage.getPaymentIfo(), "Payment Method: Check / Money Order");
		verifyEquals(ordersPage.getPaymentStatus(), "Payment Status: Pending");

		verifyEquals(ordersPage.getShippingInfoByClassName("name"), fullName);
		verifyEquals(ordersPage.getShippingInfoByClassName("email"), "Email: " + email);
		verifyEquals(ordersPage.getShippingInfoByClassName("phone"), "Phone: " + phoneNumber);
		verifyEquals(ordersPage.getShippingInfoByClassName("address1"), address1);
		verifyEquals(ordersPage.getShippingInfoByClassName("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(ordersPage.getShippingInfoByClassName("country"), country);
		verifyEquals(ordersPage.getShippingMethod(), "Shipping Method: Ground");
		verifyEquals(ordersPage.getShippingStatus(), "Shipping Status: Not yet shipped");

		verifyTrue(ordersPage.isProductNameDisplayed(productNameCheckout));
		verifyEquals(ordersPage.getUnitPrice(productNameCheckout), productPrice);
		verifyEquals(ordersPage.getQuantity(productNameCheckout), quantity);
		verifyTrue(ordersPage.isTotalDisplayed(productNameCheckout));

		verifyEquals(ordersPage.getGiftWrapping(), "Gift wrapping: No");
		verifyEquals(ordersPage.getSubTotal(), total);
		verifyEquals(ordersPage.getShipping(), "$0.00");
		verifyEquals(ordersPage.getTax(), "$0.00");
		verifyEquals(ordersPage.getOrderTotal(), total);
	}

	@Test
	public void Order_06_Checkout_Order_Payment_By_Card() {
		log.info("Order_06 - Step 01: Click to any product");
		ordersPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productNameCheckout);

		productPrice = homePage.getProductPriceValue();
		quantity = homePage.getQuantityValue();

		log.info("Order_06 - Step 02: Click to 'Add to cart' button");
		homePage.clickToButtonByClass(driver, "add-to-cart-button");

		log.info("Order_06 - Step 03: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your shopping cart");

		log.info("Order_06 - Step 04: Close bar notification");
		homePage.closeBarNotification(driver);

		log.info("Order_06 - Step 05: Open shopping cart");
		homePage.openPageAtHeader(driver, "ico-cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);

		log.info("Order_06 - Step 06: Click to Term checkbox");
		shoppingCartPage.clickToTermCheckbox();

		log.info("Order_06 - Step 07: Click to 'Checkout' button");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);

		log.info("Order_06 - Step 08: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "new-address-next-step-button");

		log.info("Order_06 - Step 09: Check to 'Ground' radio button");
		checkoutPage.clickToRadioButtonByID(driver, "shippingoption_0");

		log.info("Order_06 - Step 10: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "shipping-method-next-step-button");

		log.info("Order_06 - Step 11: Check to 'Credit Card' radio button");
		checkoutPage.clickToRadioButtonByID(driver, "paymentmethod_1");

		log.info("Order_06 - Step 12: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "payment-method-next-step-button");

		checkoutPage.selectItemInDropDownByName(driver, "CreditCardType", creditCard);
		checkoutPage.inputToTextboxByID(driver, "CardholderName", cardHolderName);
		checkoutPage.inputToTextboxByID(driver, "CardNumber", cardNumber);
		checkoutPage.selectItemInDropDownByName(driver, "ExpireMonth", expireMonth);
		checkoutPage.selectItemInDropDownByName(driver, "ExpireYear", expireYear);
		checkoutPage.inputToTextboxByID(driver, "CardCode", cardCode);

		log.info("Order_06 - Step 13: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "payment-info-next-step-button");

		log.info("Order_06 - Step 14: Verify Billing Address information");
		verifyEquals(checkoutPage.getBillingInfoByClass("name"), fullName);
		verifyEquals(checkoutPage.getBillingInfoByClass("email"), "Email: " + email);
		verifyEquals(checkoutPage.getBillingInfoByClass("phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getBillingInfoByClass("address1"), address1);
		verifyEquals(checkoutPage.getBillingInfoByClass("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(checkoutPage.getBillingInfoByClass("country"), country);
		verifyEquals(checkoutPage.getPaymentIfo(), "Payment Method: Credit Card");

		log.info("Order_06 - Step 15: Verify Shipping Address information");
		verifyEquals(checkoutPage.getShippingInfoByClass("name"), fullName);
		verifyEquals(checkoutPage.getShippingInfoByClass("email"), "Email: " + email);
		verifyEquals(checkoutPage.getShippingInfoByClass("phone"), "Phone: " + phoneNumber);
		verifyEquals(checkoutPage.getShippingInfoByClass("address1"), address1);
		verifyEquals(checkoutPage.getShippingInfoByClass("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(checkoutPage.getShippingInfoByClass("country"), country);
		verifyEquals(checkoutPage.getShippingMethod(), "Shipping Method: Ground");

		log.info("Order_06 - Step 16: Verify Product is displayed");
		verifyTrue(checkoutPage.isProductNameDisplayed(productNameCheckout));

		log.info("Order_06 - Step 17: Verify Price is displayed");
		verifyEquals(checkoutPage.getUnitPrice(productNameCheckout), productPrice);

		log.info("Order_06 - Step 18: Verify Qty. is displayed");
		verifyEquals(checkoutPage.getQuantity(productNameCheckout), quantity);

		log.info("Order_06 - Step 19: Verify Total is displayed");
		verifyTrue(checkoutPage.isTotalDisplayed(productNameCheckout));
		String total = checkoutPage.getTotal(productNameCheckout);

		checkoutPage.sleepInSecond(12);

		log.info("Order_06 - Step 20: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "confirm-order-next-step-button");

		log.info("Order_06 - Step 21: Verify success message and older number are displayed");
		verifyEquals(checkoutPage.getSuccessMessage(), "Your order has been successfully processed!");
		orderNumber = checkoutPage.getOlderNumberAtCheckoutPage();

		log.info("Order_06 - Step 22: Open 'My Account' page");
		checkoutPage.openPageAtHeader(driver, "ico-account");
		customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);

		log.info("Order_06 - Step 23: Open 'Orders' page");
		customerInfoPage.openPageAtMyAccountByPageName(driver, "Orders");
		ordersPage = PageGenerator.getOrdersPage(driver);

		log.info("Order_06 - Step 24: Verify order infomation");
		verifyTrue(ordersPage.isOrderNumberDisplayed(orderNumber));
		ordersPage.clickToDetail(orderNumber);
		verifyEquals(ordersPage.getOrderNumberAtOrdersDetail(), "ORDER #" + orderNumber);
		verifyEquals(ordersPage.getOrderStatus(), "Order Status: Pending");
		verifyEquals(ordersPage.getOrderTotalOverview(), "Order Total: " + total);

		verifyEquals(ordersPage.getBillingInfoByClassName("name"), fullName);
		verifyEquals(ordersPage.getBillingInfoByClassName("email"), "Email: " + email);
		verifyEquals(ordersPage.getBillingInfoByClassName("phone"), "Phone: " + phoneNumber);
		verifyEquals(ordersPage.getBillingInfoByClassName("address1"), address1);
		verifyEquals(ordersPage.getBillingInfoByClassName("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(ordersPage.getBillingInfoByClassName("country"), country);
		verifyEquals(ordersPage.getPaymentIfo(), "Payment Method: Credit Card");
		verifyEquals(ordersPage.getPaymentStatus(), "Payment Status: Pending");

		verifyEquals(ordersPage.getShippingInfoByClassName("name"), fullName);
		verifyEquals(ordersPage.getShippingInfoByClassName("email"), "Email: " + email);
		verifyEquals(ordersPage.getShippingInfoByClassName("phone"), "Phone: " + phoneNumber);
		verifyEquals(ordersPage.getShippingInfoByClassName("address1"), address1);
		verifyEquals(ordersPage.getShippingInfoByClassName("city-state-zip"), city + "," + zipPostalCode);
		verifyEquals(ordersPage.getShippingInfoByClassName("country"), country);
		verifyEquals(ordersPage.getShippingMethod(), "Shipping Method: Ground");
		verifyEquals(ordersPage.getShippingStatus(), "Shipping Status: Not yet shipped");

		verifyTrue(ordersPage.isProductNameDisplayed(productNameCheckout));
		verifyEquals(ordersPage.getUnitPrice(productNameCheckout), productPrice);
		verifyEquals(ordersPage.getQuantity(productNameCheckout), quantity);
		verifyTrue(ordersPage.isTotalDisplayed(productNameCheckout));

		verifyEquals(ordersPage.getGiftWrapping(), "Gift wrapping: No");
		verifyEquals(ordersPage.getSubTotal(), total);
		verifyEquals(ordersPage.getShipping(), "$0.00");
		verifyEquals(ordersPage.getTax(), "$0.00");
		verifyEquals(ordersPage.getOrderTotal(), total);
	}

	@Test
	public void Order_07_Re_Order() {
		log.info("Order_07 - Step 01: Open 'My Account' page");
		ordersPage.openPageAtHeader(driver, "ico-account");
		customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);

		log.info("Order_07 - Step 02: Open 'Orders' page");
		customerInfoPage.openPageAtMyAccountByPageName(driver, "Orders");
		ordersPage = PageGenerator.getOrdersPage(driver);

		log.info("Order_07 - Step 03: Click to 'Detail' button");
		ordersPage.clickToDetail(orderNumber);

		log.info("Order_07 - Step 04: Click to 'Re-order' button");
		ordersPage.clickToButtonByClass(driver, "re-order-button");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);

		log.info("Order_07 - Step 05: Input to quantity textbox");
		String quantity = "10";
		shoppingCartPage.inputToQuantiTyTextbox(quantity);

		log.info("Order_07 - Step 07: Click to 'Update Shopping Cart' button");
		shoppingCartPage.clickToButtonByID(driver, "updatecart");

		log.info("Order_07 - Step 08: Verify total increase $18,000.00");
		verifyTrue(shoppingCartPage.isTotalInShoppingCartPageDisplayed(productNameCheckout));

		log.info("Order_07 - Step 09: Click to Term checkbox");
		shoppingCartPage.clickToTermCheckbox();

		log.info("Order_07 - Step 10: Click to 'Checkout' button");
		shoppingCartPage.clickToButtonByID(driver, "checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);

		checkoutPage.selectItemInDropDownByName(driver, "billing_address_id", "New Address");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_FirstName", firstNameBilling);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_LastName", lastNameBilling);
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_Email", emailBilling);

		log.info("Order_07 - Step 08: Select to 'Country' dropdown");
		checkoutPage.selectToCountryDropdown(countryBilling);

		log.info("Order_07 - Step 09: Enter to 'City' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_City", cityBilling);

		log.info("Order_07 - Step 10: Enter to 'Address1' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_Address1", address1Billing);

		log.info("Order_07 - Step 11: Enter to 'Zip/postal code' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", zipPostalCodeBilling);

		log.info("Order_07 - Step 12: Enter to 'Phone number' textbox");
		checkoutPage.inputToTextboxByID(driver, "BillingNewAddress_PhoneNumber", phoneNumberBilling);

		log.info("Order_07 - Step 13: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "new-address-next-step-button");

		log.info("Order_07 - Step 14: Check to 'Next Day Air ($0.00)' radio button");
		checkoutPage.clickToRadioButtonByID(driver, "shippingoption_1");

		log.info("Order_07 - Step 15: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "shipping-method-next-step-button");

		log.info("Order_07 - Step 16: Check to 'Check / Money Order' radio button");
		checkoutPage.clickToRadioButtonByID(driver, "paymentmethod_0");

		log.info("Order_07 - Step 17: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "payment-method-next-step-button");

		log.info("Order_07 - Step 18: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "payment-info-next-step-button");

		log.info("Order_07 - Step 19: Verify Billing Address information");
		verifyEquals(checkoutPage.getBillingInfoByClass("name"), firstNameBilling + " " + lastNameBilling);
		verifyEquals(checkoutPage.getBillingInfoByClass("email"), "Email: " + emailBilling);
		verifyEquals(checkoutPage.getBillingInfoByClass("phone"), "Phone: " + phoneNumberBilling);
		verifyEquals(checkoutPage.getBillingInfoByClass("address1"), address1Billing);
		verifyEquals(checkoutPage.getBillingInfoByClass("city-state-zip"), cityBilling + "," + zipPostalCodeBilling);
		verifyEquals(checkoutPage.getBillingInfoByClass("country"), countryBilling);
		verifyEquals(checkoutPage.getPaymentIfo(), "Payment Method: Check / Money Order");

		log.info("Order_07 - Step 20: Verify Shipping Address information");
		verifyEquals(checkoutPage.getShippingInfoByClass("name"), firstNameBilling + " " + lastNameBilling);
		verifyEquals(checkoutPage.getShippingInfoByClass("email"), "Email: " + emailBilling);
		verifyEquals(checkoutPage.getShippingInfoByClass("phone"), "Phone: " + phoneNumberBilling);
		verifyEquals(checkoutPage.getShippingInfoByClass("address1"), address1Billing);
		verifyEquals(checkoutPage.getShippingInfoByClass("city-state-zip"), cityBilling + "," + zipPostalCodeBilling);
		verifyEquals(checkoutPage.getShippingInfoByClass("country"), countryBilling);
		verifyEquals(checkoutPage.getShippingMethod(), "Shipping Method: Next Day Air");

		log.info("Order_07 - Step 21: Verify Product is displayed");
		verifyTrue(checkoutPage.isProductNameDisplayed(productNameCheckout));

		log.info("Order_07 - Step 22: Verify Price is displayed");
		verifyEquals(checkoutPage.getUnitPrice(productNameCheckout), productPrice);

		log.info("Order_07 - Step 23: Verify Qty. is displayed");
		verifyEquals(checkoutPage.getQuantity(productNameCheckout), quantity);

		log.info("Order_07 - Step 24: Verify Total is displayed");
		verifyTrue(checkoutPage.isTotalDisplayed(productNameCheckout));
		String total = checkoutPage.getTotal(productNameCheckout);

		checkoutPage.sleepInSecond(15);

		log.info("Order_07 - Step 25: Click to 'Continute' button");
		checkoutPage.clickToButtonByClass(driver, "confirm-order-next-step-button");

		log.info("Order_07 - Step 26: Verify success message and older number are displayed");
		verifyEquals(checkoutPage.getSuccessMessage(), "Your order has been successfully processed!");
		orderNumber = checkoutPage.getOlderNumberAtCheckoutPage();

		log.info("Order_07 - Step 27: Open 'My Account' page");
		checkoutPage.openPageAtHeader(driver, "ico-account");
		customerInfoPage = PageGenerator.getUserCustomerInfoPage(driver);

		log.info("Order_07 - Step 28: Open 'Orders' page");
		customerInfoPage.openPageAtMyAccountByPageName(driver, "Orders");
		ordersPage = PageGenerator.getOrdersPage(driver);

		log.info("Order_07 - Step 29: Verify order infomation");
		verifyTrue(ordersPage.isOrderNumberDisplayed(orderNumber));
		ordersPage.clickToDetail(orderNumber);
		verifyEquals(ordersPage.getOrderNumberAtOrdersDetail(), "ORDER #" + orderNumber);
		verifyEquals(ordersPage.getOrderStatus(), "Order Status: Pending");
		verifyEquals(ordersPage.getOrderTotalOverview(), "Order Total: " + total);

		verifyEquals(ordersPage.getBillingInfoByClassName("name"), firstNameBilling + " " + lastNameBilling);
		verifyEquals(ordersPage.getBillingInfoByClassName("email"), "Email: " + emailBilling);
		verifyEquals(ordersPage.getBillingInfoByClassName("phone"), "Phone: " + phoneNumberBilling);
		verifyEquals(ordersPage.getBillingInfoByClassName("address1"), address1Billing);
		verifyEquals(ordersPage.getBillingInfoByClassName("city-state-zip"), cityBilling + "," + zipPostalCodeBilling);
		verifyEquals(ordersPage.getBillingInfoByClassName("country"), countryBilling);
		verifyEquals(ordersPage.getPaymentIfo(), "Payment Method: Check / Money Order");
		verifyEquals(ordersPage.getPaymentStatus(), "Payment Status: Pending");

		verifyEquals(ordersPage.getShippingInfoByClassName("name"), firstNameBilling + " " + lastNameBilling);
		verifyEquals(ordersPage.getShippingInfoByClassName("email"), "Email: " + emailBilling);
		verifyEquals(ordersPage.getShippingInfoByClassName("phone"), "Phone: " + phoneNumberBilling);
		verifyEquals(ordersPage.getShippingInfoByClassName("address1"), address1Billing);
		verifyEquals(ordersPage.getShippingInfoByClassName("city-state-zip"), cityBilling + "," + zipPostalCodeBilling);
		verifyEquals(ordersPage.getShippingInfoByClassName("country"), countryBilling);
		verifyEquals(ordersPage.getShippingMethod(), "Shipping Method: Next Day Air");
		verifyEquals(ordersPage.getShippingStatus(), "Shipping Status: Not yet shipped");

		verifyTrue(ordersPage.isProductNameDisplayed(productNameCheckout));
		verifyEquals(ordersPage.getUnitPrice(productNameCheckout), productPrice);
		verifyEquals(ordersPage.getQuantity(productNameCheckout), quantity);
		verifyTrue(ordersPage.isTotalDisplayed(productNameCheckout));
		verifyEquals(ordersPage.getGiftWrapping(), "Gift wrapping: No");
		verifyEquals(ordersPage.getSubTotal(), total);
		verifyEquals(ordersPage.getShipping(), "$0.00");
		verifyEquals(ordersPage.getTax(), "$0.00");
		verifyEquals(ordersPage.getOrderTotal(), total);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserLoginPageObject loginPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserOrdersPageObject ordersPage;
	private UserCheckoutPageObject checkoutPage;
	private String email, password, fullName;
	private String productName, productPrice, quantity, processor, ram, hdd, os, sofware1, sofware2, sofware3;
	private String editProcessor, editRam, editHdd, editOS, editQuantity, productPriceUpdate;
	private String productNameUpdate, productNameCheckout, country, city, address1, zipPostalCode, phoneNumber;
	private String creditCard, cardHolderName, cardNumber, expireMonth, expireYear, cardCode, orderNumber;
	private String firstNameBilling, lastNameBilling, emailBilling, countryBilling, cityBilling, address1Billing, zipPostalCodeBilling, phoneNumberBilling;
}
