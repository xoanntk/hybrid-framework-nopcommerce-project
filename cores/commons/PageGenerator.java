package commons;

import org.openqa.selenium.WebDriver;

import pageObject.nopComerce.admin.AdminAddNewAddressPageObject;
import pageObject.nopComerce.admin.AdminAddNewCusomerPageObject;
import pageObject.nopComerce.admin.AdminCustomersPageObject;
import pageObject.nopComerce.admin.AdminDashboardPageObject;
import pageObject.nopComerce.admin.AdminEditAddressPageObject;
import pageObject.nopComerce.admin.AdminEditCusomerPageObject;
import pageObject.nopComerce.admin.AdminLoginPageObject;
import pageObject.nopComerce.admin.AdminEditProductDetailPageObject;
import pageObject.nopComerce.admin.AdminProductsPageObject;
import pageObjects.nopComerce.user.UserAddressesPageObject;
import pageObjects.nopComerce.user.UserChangePasswordPageObject;
import pageObjects.nopComerce.user.UserCheckoutPageObject;
import pageObjects.nopComerce.user.UserCompareProductPageObject;
import pageObjects.nopComerce.user.UserCustomerInfoPageObject;
import pageObjects.nopComerce.user.UserHomePageObject;
import pageObjects.nopComerce.user.UserLoginPageObject;
import pageObjects.nopComerce.user.UserMyProductReviewPageObject;
import pageObjects.nopComerce.user.UserOrdersPageObject;
import pageObjects.nopComerce.user.UserRecentViewProductPageObject;
import pageObjects.nopComerce.user.UserRegisterPageObject;
import pageObjects.nopComerce.user.UserSearchPageObject;
import pageObjects.nopComerce.user.UserShoppingCartPageObject;
import pageObjects.nopComerce.user.UserWishListPageObject;

public class PageGenerator {

	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserAddressesPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressesPageObject(driver);
	}

	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}

	public static UserChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}

	public static UserSearchPageObject getSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}

	public static UserWishListPageObject getWishListPage(WebDriver driver) {
		return new UserWishListPageObject(driver);
	}

	public static UserShoppingCartPageObject getShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}

	public static UserCompareProductPageObject getCompareProductPage(WebDriver driver) {
		return new UserCompareProductPageObject(driver);
	}

	public static UserRecentViewProductPageObject getRecentViewProductPage(WebDriver driver) {
		return new UserRecentViewProductPageObject(driver);
	}

	public static UserCheckoutPageObject getCheckoutPage(WebDriver driver) {
		return new UserCheckoutPageObject(driver);
	}

	public static UserOrdersPageObject getOrdersPage(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminProductsPageObject getProductPage(WebDriver driver) {
		return new AdminProductsPageObject(driver);
	}

	public static AdminEditProductDetailPageObject getEditProductDetailPage(WebDriver driver) {
		return new AdminEditProductDetailPageObject(driver);
	}

	public static AdminCustomersPageObject getCustomersPage(WebDriver driver) {
		return new AdminCustomersPageObject(driver);
	}

	public static AdminAddNewCusomerPageObject getAddNewCustomerPage(WebDriver driver) {
		return new AdminAddNewCusomerPageObject(driver);
	}

	public static AdminEditCusomerPageObject getEditCustomerPage(WebDriver driver) {
		return new AdminEditCusomerPageObject(driver);
	}

	public static AdminAddNewAddressPageObject getAddNewAddressPage(WebDriver driver) {
		return new AdminAddNewAddressPageObject(driver);
	}

	public static AdminEditAddressPageObject getEditAddressPage(WebDriver driver) {
		return new AdminEditAddressPageObject(driver);
	}

}
