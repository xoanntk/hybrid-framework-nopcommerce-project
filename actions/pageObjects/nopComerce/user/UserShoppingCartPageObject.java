package pageObjects.nopComerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.user.ShoppingCartPageUI;

public class UserShoppingCartPageObject extends BasePage {
	WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameDiplayedInShoppingCartHeader(String productName) {
		hoverMouseToElement(driver, ShoppingCartPageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, ShoppingCartPageUI.PRODUCT_IN_CART_HEADER, productName);
		return isElementDisplay(driver, ShoppingCartPageUI.PRODUCT_IN_CART_HEADER, productName);
	}

	public boolean isProductNameDiplayedInShoppingCartPage(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.PRODUCT_IN_CART_PAGE, productName);
		return isElementDisplay(driver, ShoppingCartPageUI.PRODUCT_IN_CART_PAGE, productName);
	}

	public UserHomePageObject clickToEditLink() {
		waitForElementClickable(driver, ShoppingCartPageUI.EDIT_LINK);
		clickToElement(driver, ShoppingCartPageUI.EDIT_LINK);
		return PageGenerator.getUserHomePage(driver);
	}

	public String getInfoProduct(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.PRODUCT_INFO_IN_CART_PAGE, productName);
		return getElementText(driver, ShoppingCartPageUI.PRODUCT_INFO_IN_CART_PAGE, productName);
	}

	public String getUnitPriceDiplayedInShoppingCartPage(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.UNIT_PRICE_IN_CART_PAGE, productName);
		return getElementText(driver, ShoppingCartPageUI.UNIT_PRICE_IN_CART_PAGE, productName);
	}

	public String getQuantityDiplayedInShoppingCartPage(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.QUANTITY_IN_CART_PAGE, productName);
		return getElementAttribute(driver, ShoppingCartPageUI.QUANTITY_IN_CART_PAGE, "value", productName);
	}

	public String getNodataMessage() {
		waitForElementVisible(driver, ShoppingCartPageUI.NO_DATA_MESSAGE);
		return getElementText(driver, ShoppingCartPageUI.NO_DATA_MESSAGE);
	}

	public boolean isProductNameNotDiplayed(String productName) {
		try {
			waitForElementUndisplayed(driver, ShoppingCartPageUI.PRODUCT_IN_CART_PAGE, productName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void inputToQuantiTyTextbox(String quantity) {
		waitForElementVisible(driver, ShoppingCartPageUI.QUANTITY_TEXTBOX);
		senkeyToElement(driver, ShoppingCartPageUI.QUANTITY_TEXTBOX, quantity);
	}

	public void clickToTermCheckbox() {
		waitForElementClickable(driver, ShoppingCartPageUI.TERM_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, ShoppingCartPageUI.TERM_CHECKBOX);
	}

	public int getTotalInShoppingCartPage(String productName) {
		waitForElementVisible(driver, ShoppingCartPageUI.TOTAL_IN_CART_PAGE, productName);
		return Integer.parseInt(getElementText(driver, ShoppingCartPageUI.TOTAL_IN_CART_PAGE, productName).replace("$", "").replace(",", "").replace(".", ""));
	}

	public boolean isTotalInShoppingCartPageDisplayed(String productName) {
		int unitPriceCart = Integer.parseInt(getUnitPriceDiplayedInShoppingCartPage(productName).replace("$", "").replace(",", "").replace(".", ""));
		int qtyCart = Integer.parseInt(getQuantityDiplayedInShoppingCartPage(productName));
		int total = Integer.parseInt(getElementText(driver, ShoppingCartPageUI.TOTAL_IN_CART_PAGE, productName).replace("$", "").replace(",", "").replace(".", ""));
		return total == unitPriceCart * qtyCart;
	}

}
