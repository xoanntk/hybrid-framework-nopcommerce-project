package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.user.RecentViewProductPageUI;

public class UserRecentViewProductPageObject extends BasePage {
	WebDriver driver;

	public UserRecentViewProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisible(driver, RecentViewProductPageUI.PRODUCT_NAME, productName);
		return isElementDisplay(driver, RecentViewProductPageUI.PRODUCT_NAME, productName);
	}

}
