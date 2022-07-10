package pageObjects.nopComerce.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGenerator;
import pageUIs.nopComerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyAccountLinkIsDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public void clickToAnyProduct(WebDriver driver, String productName) {
		waitForElementClickable(driver, HomePageUI.DYNAMIC_PRODUCT_NAME, productName);
		clickToElement(driver, HomePageUI.DYNAMIC_PRODUCT_NAME, productName);
	}

	public void clickToAddYourReview() {
		waitForElementClickable(driver, HomePageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, HomePageUI.ADD_YOUR_REVIEW_LINK);
	}

	public void inputToTextareaReviewText(String value) {
		waitForElementVisible(driver, HomePageUI.REVIEWTEXT_TEXTAREA);
		senkeyToElement(driver, HomePageUI.REVIEWTEXT_TEXTAREA, value);
	}

	public UserMyProductReviewPageObject clickToProductReviewLink() {
		waitForElementClickable(driver, HomePageUI.PRODUCT_REVIEW_LINK);
		clickToElement(driver, HomePageUI.PRODUCT_REVIEW_LINK);
		return PageGenerator.getUserMyProductReviewPage(driver);
	}

	public void selectToSortbyDropdown(String itemValue) {
		waitForElementClickable(driver, HomePageUI.SORTBY_DROPDOWN);
		selectItemInDefaultDropdownByVisibleText(driver, HomePageUI.SORTBY_DROPDOWN, itemValue);
	}

	public boolean isProductNameSortAscending() {
		sleepInSecond(3);
		List<WebElement> productNameElements = getListWebElement(driver, HomePageUI.PRODUCTS_NAME);

		List<String> productNameText = new ArrayList<String>();

		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}

		List<String> productNameTextClone = new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}

		Collections.sort(productNameTextClone);
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		sleepInSecond(3);
		List<WebElement> productNameElements = getListWebElement(driver, HomePageUI.PRODUCTS_NAME);

		List<String> productNameText = new ArrayList<String>();

		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}

		List<String> productNameTextClone = new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}

		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		sleepInSecond(3);
		List<WebElement> productPriceElements = getListWebElement(driver, HomePageUI.PRODUCTS_PRICE);

		List<Float> productNamePrice = new ArrayList<Float>();

		for (WebElement productPrice : productPriceElements) {
			productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}

		System.out.println("Before sort asc:--------");
		for (Float productPrice : productNamePrice) {
			System.out.println(productPrice);
		}

		List<Float> productNamePriceClone = new ArrayList<Float>();
		for (Float product : productNamePrice) {
			productNamePriceClone.add(product);
		}

		Collections.sort(productNamePriceClone);

		System.out.println("After sort asc:--------");
		for (Float productPrice : productNamePriceClone) {
			System.out.println(productPrice);
		}
		return productNamePrice.equals(productNamePriceClone);
	}

	public boolean isProductPriceSortDescending() {
		sleepInSecond(3);
		List<WebElement> productPriceElements = getListWebElement(driver, HomePageUI.PRODUCTS_PRICE);

		List<Float> productNamePrice = new ArrayList<Float>();

		for (WebElement productPrice : productPriceElements) {
			productNamePrice.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}

		List<Float> productNamePriceClone = new ArrayList<Float>();
		for (Float product : productNamePrice) {
			productNamePriceClone.add(product);
		}
		Collections.sort(productNamePriceClone);
		Collections.reverse(productNamePriceClone);
		return productNamePrice.equals(productNamePriceClone);

	}

	public void selectToDisplayDropdown(String itemValue) {
		waitForElementClickable(driver, HomePageUI.DISPLAY_DROPDOWN);
		selectItemInDefaultDropdownByVisibleText(driver, HomePageUI.DISPLAY_DROPDOWN, itemValue);
	}

	public void clickToNextIcon() {
		waitForElementClickable(driver, HomePageUI.NEXT_ICON);
		clickToElement(driver, HomePageUI.NEXT_ICON);
	}

	public boolean isLessThanOr3Product() {
		sleepInSecond(3);
		return getElementSize(driver, HomePageUI.PRODUCT_ITEM) <= 3;
	}

	public boolean isLessThanOr6Product() {
		sleepInSecond(3);
		return getElementSize(driver, HomePageUI.PRODUCT_ITEM) <= 6;
	}

	public boolean isLessThanOr9Product() {
		sleepInSecond(3);
		return getElementSize(driver, HomePageUI.PRODUCT_ITEM) <= 9;
	}

	public boolean isNextIconDiplayed() {
		int numberElement = getElementSize(driver, HomePageUI.PRODUCT_ITEM);
		if (numberElement <= 3) {
			isElementDisplay(driver, HomePageUI.NEXT_ICON);
		}
		return true;
	}

	public boolean isPreviousIconDiplayed() {
		int numberElement = getElementSize(driver, HomePageUI.PRODUCT_ITEM);
		try {
			if (numberElement <= 3) {
				isElementDisplay(driver, HomePageUI.PREVIOUS_ICON);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isNextIconNotDiplayed() {
		int numberElement = getElementSize(driver, HomePageUI.PRODUCT_ITEM);
		try {
			if (numberElement <= 6 || numberElement <= 9) {
				waitForElementUndisplayed(driver, HomePageUI.NEXT_ICON);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public UserWishListPageObject clickToWishListLink() {
		waitForElementClickable(driver, HomePageUI.WISHLIST_LINK);
		clickToElement(driver, HomePageUI.WISHLIST_LINK);
		return PageGenerator.getWishListPage(driver);
	}

	public UserCompareProductPageObject clickToProductComparionLink() {
		waitForElementClickable(driver, HomePageUI.PRODUCT_COMPARION_LINK);
		clickToElement(driver, HomePageUI.PRODUCT_COMPARION_LINK);
		return PageGenerator.getCompareProductPage(driver);
	}

	public void selectToDropDownByName(String dropdownName, String itemValue) {
		waitForElementClickable(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownName);
		selectItemInDefaultDropdownByVisibleText(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownName);
	}

	public void checkToCheckboxRadioByName(String checkboxRadioName) {
		waitForElementClickable(driver, HomePageUI.DYNAMIC_CHECKBOX_RADIO_BY_TEXT, checkboxRadioName);
		checkToDefaultCheckboxRadio(driver, HomePageUI.DYNAMIC_CHECKBOX_RADIO_BY_TEXT, checkboxRadioName);
		sleepInSecond(1);
	}

	public void unCheckToCheckboxRadioByName(String checkboxRadioName) {
		waitForElementClickable(driver, HomePageUI.DYNAMIC_CHECKBOX_RADIO_BY_TEXT, checkboxRadioName);
		unCheckToDefaultCheckbox(driver, HomePageUI.DYNAMIC_CHECKBOX_RADIO_BY_TEXT, checkboxRadioName);
	}

	public String getCartQuantityOfProductAtShoppingCartHeader() {
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.COUNT_ITEMS_IN_CART_HEADER);
		return getElementText(driver, HomePageUI.CART_QUANTITY_AT_SHOPPING_CART_HEADER).replace("(", "").replace(")", "");
	}

	public String getCountItemsInShoppingCartHeader() {
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.COUNT_ITEMS_IN_CART_HEADER);
		String result = getElementText(driver, HomePageUI.COUNT_ITEMS_IN_CART_HEADER);
		String results[] = result.split(" ");
		return results[2];
	}

	public boolean isProductNameDiplayedInShoppingCartHeader(String productName) {
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.PRODUCT_NAME_IN_CART_HEADER, productName);
		return isElementDisplay(driver, HomePageUI.PRODUCT_NAME_IN_CART_HEADER, productName);
	}

	public String getInfoProduct(String productName) {
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.INFO_PRODUCT_IN_CART_HEADER, productName);
		return getElementText(driver, HomePageUI.INFO_PRODUCT_IN_CART_HEADER, productName);
	}

	public String getUnitPriceDiplayedInShoppingCartHeader(String productName) {
		sleepInSecond(3);
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.UNIT_PRICE_IN_CART_HEADER, productName);
		return getElementText(driver, HomePageUI.UNIT_PRICE_IN_CART_HEADER, productName);
	}

	public String getQuantityDiplayedInShoppingCartHeader(String productName) {
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.QUANTITY_IN_CART_HEADER, productName);
		return getElementText(driver, HomePageUI.QUANTITY_IN_CART_HEADER, productName);
	}

	public String getSubTotalInShoppingCartHeader() {
		hoverMouseToElement(driver, HomePageUI.SHOPPING_CART_LINK);
		waitForElementVisible(driver, HomePageUI.SUB_TOTAL_IN_CART_HEADER);
		return getElementText(driver, HomePageUI.SUB_TOTAL_IN_CART_HEADER);
	}

	public void inputToQuantityProduct(String value) {
		waitForElementVisible(driver, HomePageUI.QUANTITY_TEXTBOX);
		senkeyToElement(driver, HomePageUI.QUANTITY_TEXTBOX, value);
	}

	public String getProductPriceValue() {
		sleepInSecond(2);
		waitForElementVisible(driver, HomePageUI.PRODUCT_PRICE);
		return getElementText(driver, HomePageUI.PRODUCT_PRICE);
	}

	public String getQuantityValue() {
		waitForElementVisible(driver, HomePageUI.QUANTITY_TEXTBOX);
		return getElementAttribute(driver, HomePageUI.QUANTITY_TEXTBOX, "value");
	}

}
