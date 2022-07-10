package pageObject.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopComerce.admin.AdminProductsPageUI;

public class AdminProductsPageObject extends BasePage {
	private WebDriver driver;

	public AdminProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getTotalNumberProductDisplay() {
		waitForAllElementVisible(driver, AdminProductsPageUI.PRODUCTS_NAME);
		return getElementSize(driver, AdminProductsPageUI.PRODUCTS_NAME);
	}

	public boolean isProductsDiplayed(String productName, String sku, String price, String stockQuantity, String published) {
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_NAME, productName, sku, price, stockQuantity, published);
		return isElementDisplay(driver, AdminProductsPageUI.PRODUCT_NAME, productName, sku, price, stockQuantity, published);
	}

	public void unCheckToSearchSubcategoryCheckbox() {
		waitForElementClickable(driver, AdminProductsPageUI.SEARCH_SUBCATEGORY_CHECKBOX);
		unCheckToDefaultCheckbox(driver, AdminProductsPageUI.SEARCH_SUBCATEGORY_CHECKBOX);
	}

	public boolean isNoDataMessageDisplayed() {
		waitForElementVisible(driver, AdminProductsPageUI.NO_DATA_MESSAGE);
		return isElementDisplay(driver, AdminProductsPageUI.NO_DATA_MESSAGE);
	}

	public void checkToSearchSubcategoryCheckbox() {
		waitForElementClickable(driver, AdminProductsPageUI.SEARCH_SUBCATEGORY_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, AdminProductsPageUI.SEARCH_SUBCATEGORY_CHECKBOX);
	}

}
