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
import pageObjects.nopComerce.user.UserCompareProductPageObject;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserLoginPageObject;
import pageObjects.nopComerce.user.UserRecentViewProductPageObject;
import pageObjects.nopComerce.user.UserShoppingCartPageObject;
import pageObjects.nopComerce.user.UserWishListPageObject;

public class User_06_Whishlist_Compare_RecentView extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserWishListPageObject wishListPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCompareProductPageObject compareProductPage;
	private UserRecentViewProductPageObject recentViewProductPage;
	private String email, password, firstName, lastName;

	@Parameters({ "envName", "serverName", "browser", "ipAddress", "portNumber", "osName", "os_Version" })
	@BeforeClass()
	public void Login(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("localhost") String ipAddress,
			@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Conditon - Step 01: Open browser '" + browserName + "' and navigate to '" + serverName + "'");
		driver = getBrowserDriver(envName, serverName, browserName, ipAddress, portNumber, osName, osVersion);
		homePage = PageGenerator.getUserHomePage(driver);

		email = Common_01_Register.email;
		password = Common_01_Register.password;
		firstName = Common_01_Register.firstName;
		lastName = Common_01_Register.lastName;

		log.info("Pre-condition - Step 02: Navigate to Login page");
		homePage.openPageAtHeader(driver, "ico-login");
		loginPage = PageGenerator.getUserLoginPage(driver);

		log.info("Pre-condition - Step 03: Input email into 'Email' textbox");
		loginPage.inputToTextboxByID(driver, "Email", email);

		log.info("Pre-condition - Step 04: Input password into 'Password' textbox");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Pre-condition - Step 05: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		log.info("condition - Step 06: Verify 'My account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkIsDisplayed());
	}

	@Test
	public void Wishlist_01_Add_To_Wishlist() {
		String productName = "Lenovo IdeaCentre 600 All-in-One PC";

		log.info("Wishlist_01 - Step 01: Click to any product");
		homePage.openSubmenuPage(driver, "Computers ", "Desktops ");
		homePage.clickToAnyProduct(driver, productName);

		log.info("Wishlist_01 - Step 02: Click to 'Add to whishlist' button");
		homePage.clickToButtonByClass(driver, "add-to-wishlist-button");

		log.info("Wishlist_01 - Step 03: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your wishlist");

		log.info("Wishlist_01 - Step 04: Close bar notification");
		homePage.closeBarNotification(driver);

		log.info("Wishlist_01 - Step 05: Click to 'Wishlist' link");
		wishListPage = homePage.clickToWishListLink();

		log.info("Wishlist_01 - Step 06: Verify product is displayed");
		verifyTrue(wishListPage.isProductNameDiplayed(productName));

		log.info("Wishlist_01 - Step 07: Click to 'Your wishlist url for sharing'");
		wishListPage.clickToShareLink();

		log.info("Wishlist_01 - Step 08: Verify page title of wishlist");
		verifyEquals(wishListPage.getTitlePageAtWishList(), "Wishlist of " + firstName + " " + lastName);
	}

	@Test
	public void Wishlist_02_Add_Product_To_Cart_From_Wishlist() {
		String productName = "Lenovo IdeaCentre 600 All-in-One PC";

		log.info("Wishlist_02 - Step 01: Back to previous page");
		wishListPage.backToPreviousPage();

		log.info("Wishlist_02 - Step 02: Check to 'Add to cart' checkbox");
		wishListPage.checkToAddToCartCheckbox();

		log.info("Wishlist_02 - Step 03: Click to 'Add to cart' button");
		shoppingCartPage = wishListPage.clickToAddToCartButton();

		log.info("Wishlist_02 - Step 04: Verify product is displayed at 'Shopping cart' header");
		verifyTrue(shoppingCartPage.isProductNameDiplayedInShoppingCartHeader(productName));

		log.info("Wishlist_02 - Step 05: Verify product is displayed at 'Shopping cart' page");
		verifyTrue(shoppingCartPage.isProductNameDiplayedInShoppingCartPage(productName));

		log.info("Wishlist_02 - Step 06: Open 'Wishlist' page");
		shoppingCartPage.openPageAtHeader(driver, "ico-wishlist");
		wishListPage = PageGenerator.getWishListPage(driver);

		log.info("Wishlist_02 - Step 07: Verify product is not displayed at 'Wishlist' page");
		verifyEquals(wishListPage.getMessageEmptyData(), "The wishlist is empty!");

		log.info("Wishlist_02 - Step 08: Verify product is not displayed");
		verifyTrue(wishListPage.isProductNameNotDiplayed(productName));

		wishListPage.openPageAtHeader(driver, "ico-cart");
		shoppingCartPage = PageGenerator.getShoppingCartPage(driver);
		shoppingCartPage.clickToButtonByClass(driver, "remove-btn");

	}

	@Test
	public void Wishlist_03_Remove_Product_From_Wishlist() {
		String productName = "Digital Storm VANQUISH 3 Custom Performance PC";

		log.info("Wishlist_03 - Step 01: Click to any product");
		wishListPage.openSubmenuPage(driver, "Computers", "Desktops");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName);

		log.info("Wishlist_03 - Step 02: Click to 'Add to whishlist' button");
		homePage.clickToButtonByClass(driver, "add-to-wishlist-button");

		log.info("Wishlist_03 - Step 03: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your wishlist");

		log.info("Wishlist_03 - Step 04: Click to 'Wishlist' link");
		wishListPage = homePage.clickToWishListLink();

		log.info("Wishlist_03 - Step 05: Verify product is displayed");
		verifyTrue(wishListPage.isProductNameDiplayed(productName));

		log.info("Wishlist_03 - Step 06: Click to 'Remove' icon");
		wishListPage.clickToRemoveIcon();

		log.info("Wishlist_03 - Step 07: Verify product is not displayed at 'Wishlist' page");
		verifyEquals(wishListPage.getMessageEmptyData(), "The wishlist is empty!");

		log.info("Wishlist_03 - Step 08: Verify product is not displayed");
		verifyTrue(wishListPage.isProductNameNotDiplayed(productName));
	}

	@Test
	public void Wishlist_04_Add_Product_To_Compare() {
		String productName1 = "Lenovo IdeaCentre 600 All-in-One PC";
		String productPrice1 = "$500.00";
		String productName2 = "Digital Storm VANQUISH 3 Custom Performance PC";
		String productPrice2 = "$1,259.00";

		log.info("Wishlist_04 - Step 01: Click to any product");
		wishListPage.openSubmenuPage(driver, "Computers", "Desktops");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName1);

		log.info("Wishlist_04 - Step 02: Add product to Compare list");
		homePage.clickToButtonByClass(driver, "add-to-compare-list-button");

		log.info("Wishlist_04 - Step 03: Click to any product");
		homePage.openSubmenuPage(driver, "Computers ", "Desktops ");
		homePage.clickToAnyProduct(driver, productName2);

		log.info("Wishlist_04 - Step 04: Add product to Compare list");
		homePage.clickToButtonByClass(driver, "add-to-compare-list-button");

		log.info("Wishlist_04 - Step 05: Verify message is displayed");
		verifyEquals(homePage.getMessageInBarNotification(driver), "The product has been added to your product comparison");

		log.info("Wishlist_04 - Step 06: CLick to 'product comparion' link");
		compareProductPage = homePage.clickToProductComparionLink();

		log.info("Wishlist_04 - Step 07: Verify the information displayed on the product comparison page");
		verifyTrue(compareProductPage.isRemoveProductLinkDisplayed(productName1));
		verifyTrue(compareProductPage.isRemoveProductLinkDisplayed(productName2));
		verifyTrue(compareProductPage.isProductNameDisplayed(productName1));
		verifyTrue(compareProductPage.isProductNameDisplayed(productName2));
		verifyEquals(compareProductPage.getProductPrice1(), productPrice1);
		verifyEquals(compareProductPage.getProductPrice2(), productPrice2);
		verifyTrue(compareProductPage.isClearListButtonDisplayed());

		log.info("Wishlist_04 - Step 08: Click to 'Clear List' button");
		compareProductPage.clickToClearListButton();

		log.info("Wishlist_04 - Step 09: Verify message is displayed");
		verifyEquals(compareProductPage.getMessageDisplayed(), "You have no items to compare.");

		log.info("Wishlist_04 - Step 10: Verify product is not displayed on comparison page");
		verifyTrue(compareProductPage.isProductName1NotDiplayed(productName1));
		verifyTrue(compareProductPage.isProductName2NotDiplayed(productName2));
	}

	@Test
	public void Wishlist_05_Recently_Viewed_Products() {
		String productName1 = "Apple MacBook Pro 13-inch";
		String productName2 = "Asus N551JK-XO076H Laptop";
		String productName3 = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
		String productName4 = "HP Spectre XT Pro UltraBook";
		String productName5 = "Lenovo Thinkpad X1 Carbon Laptop";

		log.info("Wishlist_05 - Step 01: View detail of 5 any products");
		compareProductPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName1);

		compareProductPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName2);

		compareProductPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName3);

		compareProductPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName4);

		compareProductPage.openSubmenuPage(driver, "Computers", "Notebooks");
		homePage = PageGenerator.getUserHomePage(driver);
		homePage.clickToAnyProduct(driver, productName5);

		log.info("Wishlist_05 - Step 02: Click to 'Recently viewed products' at footer");
		homePage.openPageAtFooter(driver, "Recently viewed products");
		recentViewProductPage = PageGenerator.getRecentViewProductPage(driver);

		log.info("Wishlist_05 - Step 03: Verify only the last 3 viewed products are displayed");
		verifyTrue(recentViewProductPage.isProductNameDisplayed(productName3));
		verifyTrue(recentViewProductPage.isProductNameDisplayed(productName4));
		verifyTrue(recentViewProductPage.isProductNameDisplayed(productName5));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
