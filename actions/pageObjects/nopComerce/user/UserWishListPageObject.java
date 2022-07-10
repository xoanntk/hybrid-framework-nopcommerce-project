package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.user.WishlistPageUI;

public class UserWishListPageObject extends BasePage {
	WebDriver driver;

	public UserWishListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameDiplayed(String productName) {
		waitForElementVisible(driver, WishlistPageUI.DYNAMIC_PRODUCT_NAME, productName);
		return isElementDisplay(driver, WishlistPageUI.DYNAMIC_PRODUCT_NAME, productName);
	}

	public boolean isProductNameNotDiplayed(String productName) {
		try {
			waitForElementUndisplayed(driver, WishlistPageUI.DYNAMIC_PRODUCT_NAME, productName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickToShareLink() {
		waitForElementClickable(driver, WishlistPageUI.SHARING_URL);
		clickToElement(driver, WishlistPageUI.SHARING_URL);
	}

	public String getTitlePageAtWishList() {
		waitForElementVisible(driver, WishlistPageUI.TITLE_PAGE);
		return getElementText(driver, WishlistPageUI.TITLE_PAGE);
	}

	public void checkToAddToCartCheckbox() {
		waitForElementClickable(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, WishlistPageUI.ADD_TO_CART_CHECKBOX);
	}

	public UserShoppingCartPageObject clickToAddToCartButton() {
		waitForElementClickable(driver, WishlistPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, WishlistPageUI.ADD_TO_CART_BUTTON);
		return PageGenerator.getShoppingCartPage(driver);
	}

	public void backToPreviousPage() {
		backToPage(driver);
	}

	public String getMessageEmptyData() {
		waitForElementVisible(driver, WishlistPageUI.EMPTY_DATA_MESSAGE);
		return getElementText(driver, WishlistPageUI.EMPTY_DATA_MESSAGE);
	}

	public void clickToRemoveIcon() {
		waitForElementClickable(driver, WishlistPageUI.REMOVE_ICON);
		clickToElement(driver, WishlistPageUI.REMOVE_ICON);
	}

}
