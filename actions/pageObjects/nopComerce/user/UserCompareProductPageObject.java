package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.user.CompareProductPageUI;
import pageUIs.nopComerce.user.WishlistPageUI;

public class UserCompareProductPageObject extends BasePage {
	WebDriver driver;

	public UserCompareProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRemoveProductLinkDisplayed(String productName) {
		waitForElementClickable(driver, CompareProductPageUI.DYNAMIC_REMOVE_PRODUCT_LINK, productName);
		return isElementDisplay(driver, CompareProductPageUI.DYNAMIC_REMOVE_PRODUCT_LINK, productName);
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisible(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME, productName);
		return isElementDisplay(driver, CompareProductPageUI.DYNAMIC_PRODUCT_NAME, productName);
	}

	public String getProductPrice1() {
		waitForElementVisible(driver, CompareProductPageUI.PRODUCT_PRICE1);
		return getElementText(driver, CompareProductPageUI.PRODUCT_PRICE1);
	}

	public String getProductPrice2() {
		waitForElementVisible(driver, CompareProductPageUI.PRODUCT_PRICE2);
		return getElementText(driver, CompareProductPageUI.PRODUCT_PRICE2);
	}

	public boolean isClearListButtonDisplayed() {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		return isElementDisplay(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
	}

	public void clickToClearListButton() {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_BUTTON);
	}

	public String getMessageDisplayed() {
		waitForElementVisible(driver, CompareProductPageUI.NO_DATA_MESSAGE);
		return getElementText(driver, CompareProductPageUI.NO_DATA_MESSAGE);
	}

	public boolean isProductName1NotDiplayed(String productName) {
		waitForElementUndisplayed(driver, WishlistPageUI.DYNAMIC_PRODUCT_NAME, productName);
		return true;
	}

	public boolean isProductName2NotDiplayed(String productName) {
		waitForElementUndisplayed(driver, WishlistPageUI.DYNAMIC_PRODUCT_NAME, productName);
		return true;
	}

}
